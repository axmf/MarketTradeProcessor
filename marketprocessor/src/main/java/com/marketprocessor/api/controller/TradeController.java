package com.marketprocessor.api.controller;

import com.marketprocessor.api.controller.model.Trade;
import com.marketprocessor.processor.TradeProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;


@RestController
@RequiredArgsConstructor
@Slf4j
public class TradeController {

    private final TradeProcessor tradeProcessor;

    @PostMapping(value = "/trade")
    public void newTrade(@RequestBody Trade trade) {
        log.debug("Trade received: {}", trade);
        tradeProcessor.processTrade(trade);
    }

    @GetMapping(value = "/trade")
    public HashMap<UUID, Trade> trades() {
        return tradeProcessor.getTrades();
    }
}