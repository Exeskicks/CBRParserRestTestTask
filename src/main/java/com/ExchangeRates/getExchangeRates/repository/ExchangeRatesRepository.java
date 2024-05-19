package com.ExchangeRates.getExchangeRates.repository;


import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Long> {

    Optional<ExchangeRates> findByCharCodeAndDate(String charCode, LocalDate date);

    Boolean existsByCharCodeAndDate(String charCode, LocalDate date);
}
