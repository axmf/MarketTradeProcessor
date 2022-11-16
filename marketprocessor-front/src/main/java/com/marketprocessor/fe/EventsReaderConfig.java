package com.marketprocessor.fe;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marketprocessor.fe.model.UserVolumeEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.time.Instant;
import java.util.function.Consumer;


@Configuration
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Slf4j
public class EventsReaderConfig {

    //    private final UserController userController;
    @Autowired(required = false)
    private SimpMessagingTemplate template;


    @Bean
    public ObjectMapper defaultMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public Consumer<UserVolumeEvent> sendEventsToClient() {
        return uve -> {
            log.info(Instant.now() + ": " + uve);
            greet(uve);
        };
    }

    public void greet(UserVolumeEvent uve) {
        this.template.convertAndSend("/topic/volume", uve);
    }
}
