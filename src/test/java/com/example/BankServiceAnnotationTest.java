package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class BankServiceAnnotationTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private BankService bankService;

    @Test
    void depositAddsToExistingBalance() {
        BankAccount account = new BankAccount();
        account.deposit(500);

        bankService.deposit(account, 1000);

        assertEquals(1500.0, account.getBalance(), 0.000001);
        verify(notificationService).send("Sikeresen befizetve");
    }

    @Test
    void everySuccessfulDepositSendsNotification() {
        BankAccount account = new BankAccount();

        bankService.deposit(account, 1000);
        bankService.deposit(account, 500);

        assertEquals(1500.0, account.getBalance(), 0.000001);
        verify(notificationService, times(2)).send("Sikeresen befizetve");
    }

    @Test
    void failedDepositDoesNotSendNotification() {
        BankAccount account = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> bankService.deposit(account, -100));

        assertEquals(0.0, account.getBalance(), 0.000001);
        verifyNoInteractions(notificationService);
    }
}
