package com.marketprocessor.api.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
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
