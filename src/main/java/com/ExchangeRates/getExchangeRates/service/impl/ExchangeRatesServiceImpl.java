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
    public ExchangeRates saveExchangeRates(ExchangeRatesDto exchangeRatesDto) {

        ExchangeRates exchangeRates = ExchangeRates.builder()
            .numCode(exchangeRatesDto.charCode())
            .date(LocalDate.now())
            .value(exchangeRatesDto.value())
            .build();


       exchangeRatesRepository.save(exchangeRates);

        return exchangeRates;
    }

    @Override
    public Optional<ExchangeRates> getExchangeRates(String charCode, LocalDate date){

        Optional<ExchangeRates> exchangeRate = exchangeRatesRepository.findByCharCodeAndDate(charCode, date);
        if (exchangeRate.isEmpty()) {
              currencyService.fetchAndSaveCurrencies(date);
              exchangeRate = exchangeRatesRepository.findByCharCodeAndDate(charCode, date);
        }
        return exchangeRate;
    }
}
