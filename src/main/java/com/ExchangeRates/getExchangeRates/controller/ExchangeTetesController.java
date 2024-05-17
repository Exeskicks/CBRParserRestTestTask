package com.ExchangeRates.getExchangeRates.controller;


import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.service.CurrencyService;
import com.ExchangeRates.getExchangeRates.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/exc")
@AllArgsConstructor
public class ExchangeTetesController {

    private final ExchangeRatesService exchangeRatesService;
    private final CurrencyService currencyService;

    @PostMapping("saved")
    public ResponseEntity<?> saveExchange(@RequestBody ExchangeRatesDto exchangeRatesDto)
    {
        return ResponseEntity.ok(exchangeRatesService.saveExchangeRates(exchangeRatesDto));
    }

    @GetMapping
    public String all(@RequestParam(defaultValue = "DefaultCharCode") String CharCode,
                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return "код + " + CharCode + "\n data " + date;
    }


}
