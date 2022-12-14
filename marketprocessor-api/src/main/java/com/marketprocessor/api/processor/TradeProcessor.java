package com.marketprocessor.api.processor;

import com.marketprocessor.api.model.Trade;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TradeProcessor {

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void processTrade(Trade trade) {
        kafkaTemplate.send("trades", trade.getUserId(), trade);
    }

}
