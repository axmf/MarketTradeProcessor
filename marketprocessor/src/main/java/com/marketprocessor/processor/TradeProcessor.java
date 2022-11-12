package com.marketprocessor.processor;

import com.marketprocessor.api.controller.model.Trade;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class TradeProcessor {

    private Map<UUID, Trade> trades;

    public void processTrade(Trade trade) {
        //TODO add a DB
        trades.put(UUID.randomUUID(), trade);
    }

}
