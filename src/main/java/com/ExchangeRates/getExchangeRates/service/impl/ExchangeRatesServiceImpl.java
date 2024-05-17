package com.ExchangeRates.getExchangeRates.service.impl;

import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import com.ExchangeRates.getExchangeRates.repository.ExchangeRatesRepository;
import com.ExchangeRates.getExchangeRates.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;


@Service
@AllArgsConstructor
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRatesRepository exchangeRatesRepository;

    @Override
    public ExchangeRates saveExchangeRates(ExchangeRatesDto exchangeRatesDto) {

        ExchangeRates exchangeRates = ExchangeRates.builder()
            .num_code(exchangeRatesDto.num_code())
            .date(LocalDate.now())
            .value(exchangeRatesDto.value())
            .build();


       exchangeRatesRepository.save(exchangeRates);

        return exchangeRates;
    }
}
