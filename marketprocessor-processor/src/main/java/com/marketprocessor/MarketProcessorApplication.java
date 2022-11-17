package com.marketprocessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marketprocessor.model.Trade;
import com.marketprocessor.model.UserVolumeEvent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.GroupedFlux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

@SpringBootApplication
public class MarketProcessorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketProcessorApplication.class, args);
    }

}
