package com.marketprocessor.api;

import com.marketprocessor.api.model.Trade;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.ByteArrayDeserializer;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(topics = {MarketProcessorApiApplicationTests.TRADES, MarketProcessorApiApplicationTests.NUMBER_OF_OPERATIONS},
        partitions = 1,
        bootstrapServersProperty = "spring.kafka.bootstrap-servers")
class MarketProcessorApiApplicationTests {


    public static final String TRADES = "trades";
    public static final String NUMBER_OF_OPERATIONS = "numberOfOperations";

    @Test
    void testSendReceive(@Autowired EmbeddedKafkaBroker embeddedKafka) {
        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka);
        senderProps.put("key.serializer", ByteArraySerializer.class);
        senderProps.put("value.serializer", ByteArraySerializer.class);
        DefaultKafkaProducerFactory<UUID, Trade> pf = new DefaultKafkaProducerFactory<>(senderProps);
        KafkaTemplate<UUID, Trade> template = new KafkaTemplate<>(pf, true);
        template.setDefaultTopic(TRADES);

        Trade t = Trade.builder().userId(123L).build();

        template.sendDefault(UUID.randomUUID(), t);

        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("groupName", "false", embeddedKafka);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put("key.deserializer", ByteArrayDeserializer.class);
        consumerProps.put("value.deserializer", ByteArrayDeserializer.class);
        DefaultKafkaConsumerFactory<byte[], byte[]> cf = new DefaultKafkaConsumerFactory<>(consumerProps);

        Consumer<byte[], byte[]> consumer = cf.createConsumer();
        consumer.assign(Collections.singleton(new TopicPartition(NUMBER_OF_OPERATIONS, 0)));
        ConsumerRecords<byte[], byte[]> records = consumer.poll(Duration.ofSeconds(10));
        consumer.commitSync();

        assertThat(records.count()).isEqualTo(1);
        assertThat(new String(records.iterator().next().value())).isEqualTo("FOO");
    }

}
