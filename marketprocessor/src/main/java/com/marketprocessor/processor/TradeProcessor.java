package com.marketprocessor.processor;

import com.marketprocessor.api.controller.model.Trade;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class TradeProcessor {

    private final Map<UUID, Trade> trades = new HashMap<>();

    public void processTrade(Trade trade) {
        //TODO add a DB
        trades.put(UUID.randomUUID(), trade);
    }

    public HashMap<UUID, Trade> getTrades() {
        return new HashMap<>(trades);
    }

}
