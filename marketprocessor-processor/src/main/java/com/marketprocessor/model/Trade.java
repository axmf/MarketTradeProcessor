package com.marketprocessor.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Data
@Jacksonized
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade {

    Long userId;
    String currencyFrom;
    String currencyTo;
    Long amountSell;
    Long amountBuy;
    Float rate;
    Instant timePlaced;
    String originatingCountry;

}
