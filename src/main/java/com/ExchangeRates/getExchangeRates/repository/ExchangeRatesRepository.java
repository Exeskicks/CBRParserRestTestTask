package com.ExchangeRates.getExchangeRates.repository;


import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRatesRepository extends JpaRepository<ExchangeRates, Integer> {


}
