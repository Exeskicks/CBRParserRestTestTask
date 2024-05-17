package com.ExchangeRates.getExchangeRates.service;


import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import org.springframework.stereotype.Service;


public interface ExchangeRatesService {
                 
    ExchangeRates saveExchangeRates(ExchangeRatesDto exchangeRatesDto);
    
}
