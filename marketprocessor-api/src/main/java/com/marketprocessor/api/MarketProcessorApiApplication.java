package com.marketprocessor.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MarketProcessorApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketProcessorApiApplication.class, args);
    }
}
