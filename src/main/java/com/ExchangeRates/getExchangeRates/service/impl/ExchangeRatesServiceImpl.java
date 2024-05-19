package com.ExchangeRates.getExchangeRates.service.impl;

import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import com.ExchangeRates.getExchangeRates.repository.ExchangeRatesRepository;
import com.ExchangeRates.getExchangeRates.service.CurrencyService;
import com.ExchangeRates.getExchangeRates.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;
    private final CurrencyService currencyService;

    @Override
    public Optional<ExchangeRatesDto> getExchangeRates(String charCode, LocalDate date){

        currencyService.fetchAndSaveCurrencies(date);
        Optional<ExchangeRates> exchangeRates = exchangeRatesRepository.findByCharCodeAndDate(charCode, date);

        return exchangeRates.map(er -> ExchangeRatesDto.builder()
            .charCode(er.getCharCode())
            .date(er.getDate())
            .value(er.getValue())
            .build());
    }
}
