package com.marketprocessor.api.controller.model;

import lombok.Data;

import java.time.Instant;

@Data
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
