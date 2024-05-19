package com.ExchangeRates.getExchangeRates.parser;


import jakarta.xml.bind.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ValCurs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValCurs  {

    @XmlAttribute(name = "Date")
    private String date;

    @XmlAttribute(name = "Name")
    private String name;

    @XmlElement(name = "Valute")
    private List<Valute> valutes;
}
