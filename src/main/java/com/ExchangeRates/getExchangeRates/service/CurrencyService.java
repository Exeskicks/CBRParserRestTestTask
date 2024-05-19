package com.ExchangeRates.getExchangeRates.service;

import java.time.LocalDate;

public interface CurrencyService {

    void fetchAndSaveCurrencies(LocalDate date);
}
