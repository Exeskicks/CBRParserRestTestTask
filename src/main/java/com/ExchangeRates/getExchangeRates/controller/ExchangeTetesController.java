package com.ExchangeRates.getExchangeRates.controller;


import com.ExchangeRates.getExchangeRates.dto.ExchangeRatesDto;
import com.ExchangeRates.getExchangeRates.service.CurrencyService;
import com.ExchangeRates.getExchangeRates.service.ExchangeRatesService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/exchange")
@AllArgsConstructor
public class ExchangeTetesController {

    private final ExchangeRatesService exchangeRatesService;


    @GetMapping
    public ResponseEntity<?> getCurrencyRate(@RequestParam String charCode,
                      @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dsd",
                      iso = DateTimeFormat.ISO.DATE) LocalDate date)
    {
        return ResponseEntity.ok(exchangeRatesService.getExchangeRates(charCode, date));
    }


}
