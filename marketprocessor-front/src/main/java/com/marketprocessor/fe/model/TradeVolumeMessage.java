package com.marketprocessor.fe.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TradeVolumeMessage {

    String timestamp;
    Long userId;
    Long operationsCount;

}
