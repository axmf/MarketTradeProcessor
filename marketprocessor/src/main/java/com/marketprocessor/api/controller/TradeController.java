package com.marketprocessor.api.controller;

import com.marketprocessor.api.controller.model.Trade;
import com.marketprocessor.processor.TradeProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class TradeController {

    private final TradeProcessor tradeProcessor;

    @PostMapping(value = "/trade")
    public void postMethodName(@RequestBody Trade trade) {
        log.debug("Trade received: {}", trade);
        tradeProcessor.processTrade(trade);
    }
}