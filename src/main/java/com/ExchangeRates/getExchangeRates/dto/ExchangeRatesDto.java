package com.ExchangeRates.getExchangeRates.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRatesDto(
    String charCode,
    LocalDate date,
    BigDecimal value
) {
}
