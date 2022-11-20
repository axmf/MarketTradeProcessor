package com.marketprocessor.api;

import com.marketprocessor.api.controller.TradeController;
import com.marketprocessor.api.model.Trade;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = {MarketProcessorApiApplicationTests.TRADES}, partitions = 1, bootstrapServersProperty = "spring.kafka.bootstrap-servers")
class MarketProcessorApiApplicationTests {


    @Autowired
    TradeController tradeController;
    public static final String TRADES = "trades";

    @Test
    void testSendReceive(@Autowired EmbeddedKafkaBroker embeddedKafka) {
        Trade expectedTrade = Trade.builder().userId(123L)
                .amountBuy(100L)
                .amountSell(200L)
                .currencyFrom("EUR")
                .rate((float) 9.23)
                .currencyTo("GBP")
                .originatingCountry("ES")
                .timePlaced(Instant.now())
                .build();
        tradeController.newTrade(expectedTrade);

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("groupName", "false", embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        JsonDeserializer<Trade> valueDeserializer = new JsonDeserializer<>(Trade.class);
        valueDeserializer.setRemoveTypeHeaders(false);
        valueDeserializer.addTrustedPackages("*");
        valueDeserializer.setUseTypeMapperForKey(true);
        DefaultKafkaConsumerFactory<Long, Trade> cf = new DefaultKafkaConsumerFactory<>(consumerProps, new LongDeserializer(), valueDeserializer);

        Consumer<Long, Trade> consumer = cf.createConsumer();
        consumer.assign(Collections.singleton(new TopicPartition(TRADES, 0)));
        ConsumerRecords<Long, Trade> records = consumer.poll(Duration.ofSeconds(10));
        consumer.commitSync();

        assertThat(records.count()).isEqualTo(1);
        ConsumerRecord<Long, Trade> actualTrade = records.iterator().next();
        assertThat(actualTrade.key()).isEqualTo(expectedTrade.getUserId());
        assertThat(actualTrade.value()).isEqualTo(expectedTrade);
    }

}
