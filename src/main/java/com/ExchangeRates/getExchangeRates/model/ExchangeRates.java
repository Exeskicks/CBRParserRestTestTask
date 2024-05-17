package com.ExchangeRates.getExchangeRates.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "exchange_rates")
@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String num_code; //Код валюты в формате. согласно ISO 4217
    String charCode; //Символьный код
    LocalDate date; //Дата на которую нужно получить курс
    BigDecimal value; //, значение в паре с рублем
}
