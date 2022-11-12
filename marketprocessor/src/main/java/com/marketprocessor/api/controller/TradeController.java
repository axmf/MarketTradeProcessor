package com.marketprocessor.api.controller;

import com.marketprocessor.api.controller.model.Trade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TradeController {

    @PostMapping(value = "/trade")
    public void postMethodName(@RequestBody Trade trade) {
        //TODO: process POST request
        log.info("Trade received: {}", trade);
    }


}