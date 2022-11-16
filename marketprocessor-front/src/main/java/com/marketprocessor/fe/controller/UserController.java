package com.marketprocessor.fe.controller;

import com.marketprocessor.fe.model.UserVolumeEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequiredArgsConstructor
public class UserController {

//    private final SimpMessagingTemplate template;
//
//
////    @RequestMapping(path = "/greetings", method = POST)
//    public void greet(UserVolumeEvent uve) {
//        String text = "[" + Instant.now() + "]:" + uve;
//        this.template.convertAndSend("/topic/volume", text);
//    }


}
