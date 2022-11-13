package com.marketprocessor.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(topics = {"trades"},
        partitions = 1,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers")
class MarketProcessorApiApplicationTests {

    @Test
    void contextLoads() {
    }

}
