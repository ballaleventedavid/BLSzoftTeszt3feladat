package com.example;

public class CurrencyConverter {

    private final ExchangeRateService rateService;

    public CurrencyConverter(ExchangeRateService rateService) {
        this.rateService = rateService;
    }

    public double convert(double amount, String from, String to) {
        double rate = rateService.getRate(from, to);
        return amount * rate;
    }
}
