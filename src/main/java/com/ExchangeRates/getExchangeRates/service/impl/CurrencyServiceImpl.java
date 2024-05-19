package com.ExchangeRates.getExchangeRates.service.impl;

import com.ExchangeRates.getExchangeRates.model.ExchangeRates;
import com.ExchangeRates.getExchangeRates.parser.ValCurs;
import com.ExchangeRates.getExchangeRates.parser.Valute;
import com.ExchangeRates.getExchangeRates.repository.ExchangeRatesRepository;
import com.ExchangeRates.getExchangeRates.service.CurrencyService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
@Configuration
@EnableScheduling
@Slf4j
public class CurrencyServiceImpl implements CurrencyService {

    private final ExchangeRatesRepository exchangeRatesRepository;


    public void fetchAndSaveCurrencies(LocalDate date) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(dateTimeFormatter);
        try {
            String baseUrl = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=%s";
            String url = String.format(baseUrl, formattedDate);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(url, String.class);

            JAXBContext jaxbContext = JAXBContext.newInstance(ValCurs.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(result);
            ValCurs valCurs = (ValCurs) unmarshaller.unmarshal(reader);

            List<Valute> valuteList = valCurs.getValutes();

            DateTimeFormatter dateTimeFormatterl = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate parsedDate = LocalDate.parse(valCurs.getDate(), dateTimeFormatterl);

            List<ExchangeRates> exchangeRatesList = valuteList.stream()
                .filter(valute -> !exchangeRatesRepository
                    .existsByCharCodeAndDate(valute.getCharCode(), parsedDate))


                .map(valute -> {

                    return ExchangeRates.builder()
                        .numCode(valute.getNumCode())
                        .charCode(valute.getCharCode())
                        .date(date)
                        .value(new BigDecimal(valute.getValueRate().replace(",", ".")))
                        .build();
                })
                .toList();
            exchangeRatesRepository.saveAll(exchangeRatesList);

        } catch (JAXBException e) {
            log.info(String.valueOf(e));
        }
    }

    @Scheduled(cron = "* * 1 * * *")
    private void fetchAndSaveCurrencies(){
        fetchAndSaveCurrencies(LocalDate.now());
    }
}