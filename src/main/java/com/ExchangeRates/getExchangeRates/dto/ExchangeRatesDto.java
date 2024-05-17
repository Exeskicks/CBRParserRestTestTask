package com.ExchangeRates.getExchangeRates.dto;

import java.math.BigDecimal;
import java.util.Date;

public record ExchangeRatesDto(
    String num_code,
    Date date,
    BigDecimal value
) {
}
