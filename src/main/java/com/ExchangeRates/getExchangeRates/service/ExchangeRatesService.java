package com.ExchangeRates.getExchangeRates.service;


import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;

import java.time.LocalDate;
import java.util.Optional;


public interface ExchangeRatesService {


    Optional<ExchangeRatesDto> getExchangeRates(String charCode, LocalDate date);
    
}
