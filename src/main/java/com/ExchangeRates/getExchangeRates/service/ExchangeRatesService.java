package com.ExchangeRates.getExchangeRates.service;


import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.model.ExchangeRates;

import java.time.LocalDate;
import java.util.Optional;


public interface ExchangeRatesService {
                 
    ExchangeRates saveExchangeRates(ExchangeRatesDto exchangeRatesDto);

    Optional<ExchangeRates> getExchangeRates(String charCode, LocalDate date);
    
}
