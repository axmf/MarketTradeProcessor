package com.marketprocessor.function;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marketprocessor.model.Trade;
import com.marketprocessor.model.UserVolumeEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
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
    public Function<Flux<Trade>, Flux<UserVolumeEvent>> calculateNumberOfOperations() {
        return data -> data.window(Duration.ofSeconds(3))
                .flatMap(window -> window.groupBy(Trade::getUserId)
                        .flatMap(this::calculateOperations));
    }

    private Mono<UserVolumeEvent> calculateOperations(GroupedFlux<Long, Trade> group) {
        return group
                .count()
                .map(c -> new UserVolumeEvent(Instant.now(), group.key(), c));
    }
}
