package com.ExchangeRates.getExchangeRates.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExchangeRatesDto{

    String charCode;
    LocalDate date;
    BigDecimal value;
}
