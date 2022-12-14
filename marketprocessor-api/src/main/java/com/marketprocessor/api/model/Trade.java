package com.marketprocessor.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
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
