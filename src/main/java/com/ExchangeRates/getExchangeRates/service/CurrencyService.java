package com.ExchangeRates.getExchangeRates.service;

import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import com.ExchangeRates.getExchangeRates.parser.ValCurs;
import com.ExchangeRates.getExchangeRates.parser.Valute;
import com.ExchangeRates.getExchangeRates.repository.ExchangeRatesRepository;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CurrencyService {

    private final ExchangeRatesRepository exchangeRatesRepository;


    public void fetchAndSaveCurrencies() {
        try {
            String url = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=17/05/2024";
            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(result);
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(reader);

            List<Valute> valuteList = valCurs.getValutes();

            List<ExchangeRates> exchangeRatesList = valuteList.stream()
                .map(valute -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

                    LocalDate parsedDate = LocalDate.parse(valCurs.getDate(), formatter);

                    return ExchangeRates.builder()
                        .num_code(valute.getNumCode())
                        .charCode(valute.getCharCode())
                        .date(parsedDate)
                        .value(new BigDecimal(valute.getValuerate().replace(",", ".")))
                        .build();
                })
                .toList();
            exchangeRatesRepository.saveAll(exchangeRatesList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}