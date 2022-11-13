package com.marketprocessor.api.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    //TODO setup error handling and dlq

    @Bean
    public NewTopic tradesTopic() {
        short replicationFactor = 1;
        int numPartitions = 1;
        return new NewTopic("trades", numPartitions, replicationFactor);
    }
}