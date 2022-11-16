package com.marketprocessor.fe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.util.function.Function;

@SpringBootApplication
public class MarketProcessorFrontEnd {

	public static void main(String[] args) {
		SpringApplication.run(MarketProcessorFrontEnd.class, args);
	}

}
