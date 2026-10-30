package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    @Test
    void newAccountHasZeroBalance() {
        BankAccount account = new BankAccount();

        assertEquals(0.0, account.getBalance(), 0.000001);
    }

    @Test
    void depositIncreasesBalance() {
        BankAccount account = new BankAccount();

        account.deposit(1000);

        assertEquals(1000.0, account.getBalance(), 0.000001);
    }

    @Test
    void multipleDepositsAreAddedTogether() {
        BankAccount account = new BankAccount();

        account.deposit(1000);
        account.deposit(500.25);

        assertEquals(1500.25, account.getBalance(), 0.000001);
    }

    @ParameterizedTest(name = "Érvénytelen összeg")
    @ValueSource(doubles = { -100.0, 0.0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY })
    void invalidDepositDoesNotChangeBalance(double amount) {
        BankAccount account = new BankAccount();
        account.deposit(500);

        assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
        assertEquals(500.0, account.getBalance(), 0.000001);
    }

    @Test
    void withdrawalDecreasesBalance() {
        BankAccount account = new BankAccount();
        account.deposit(1000);

        account.withdraw(400);

        assertEquals(600.0, account.getBalance(), 0.000001);
    }

    @Test
    void entireBalanceCanBeWithdrawn() {
        BankAccount account = new BankAccount();
        account.deposit(1000);

        account.withdraw(1000);

        assertEquals(0.0, account.getBalance(), 0.000001);
    }

    @Test
    void withdrawalAboveBalanceDoesNotChangeBalance() {
        BankAccount account = new BankAccount();
        account.deposit(1000);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1001));

        assertEquals(1000.0, account.getBalance(), 0.000001);
    }

    @ParameterizedTest(name = "Érvénytelen pénzfelvételi összeg: {0}")
    @ValueSource(doubles = { -100.0, 0.0, Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY })
    void invalidWithdrawalDoesNotChangeBalance(double amount) {
        BankAccount account = new BankAccount();
        account.deposit(1000);

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));

        assertEquals(1000.0, account.getBalance(), 0.000001);
    }

    @Test
    void withdrawalFromEmptyAccountDoesNotChangeBalance() {
        BankAccount account = new BankAccount();

        assertThrows(IllegalArgumentException.class, () -> account.withdraw(1));

        assertEquals(0.0, account.getBalance(), 0.000001);
    }

    @Test
    void fractionalAmountCanBeWithdrawn() {
        BankAccount account = new BankAccount();
        account.deposit(1000.5);

        account.withdraw(250.25);

        assertEquals(750.25, account.getBalance(), 0.000001);
    }
}
