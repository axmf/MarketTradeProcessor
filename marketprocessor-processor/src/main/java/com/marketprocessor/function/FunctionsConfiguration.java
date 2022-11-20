package com.marketprocessor.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marketprocessor.model.Trade;
import com.marketprocessor.model.UserVolumeEvent;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.function.Function;

@Configuration
public class FunctionsConfiguration {

    @Bean
    public ObjectMapper defaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public Function<KStream<Long, Trade>, KStream<Long, UserVolumeEvent>> calculateNumberOfOperations() {
        return input -> input.map((k, v) -> new KeyValue<>(v.getUserId(), v.getAmountBuy()))
                .groupByKey(Grouped.with(Serdes.Long(), Serdes.Long()))
                .count()
                .toStream()
                .map((k, v) -> new KeyValue<>(k, new UserVolumeEvent(Instant.now(), k, v)));
    }
}
