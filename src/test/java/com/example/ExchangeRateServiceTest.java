package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateService rateService;

    @InjectMocks
    private CurrencyConverter converter;

    @Test
    void convertsEurosToForintsUsingMockedRate() {
        when(rateService.getRate("EUR", "HUF")).thenReturn(400.0);

        double result = converter.convert(100, "EUR", "HUF");

        assertEquals(40000.0, result, 0.000001);
        verify(rateService).getRate("EUR", "HUF");
    }

    @Test
    void passesRequestedCurrencyCodesToRateService() {
        when(rateService.getRate("USD", "HUF")).thenReturn(350.0);

        double result = converter.convert(50, "USD", "HUF");

        assertEquals(17500.0, result, 0.000001);
        verify(rateService).getRate("USD", "HUF");
    }

    @Test
    void supportsFractionalExchangeRate() {
        when(rateService.getRate("HUF", "EUR")).thenReturn(0.0025);

        double result = converter.convert(1000, "HUF", "EUR");

        assertEquals(2.5, result, 0.000001);
        verify(rateService).getRate("HUF", "EUR");
    }
}
