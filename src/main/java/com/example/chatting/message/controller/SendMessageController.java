package com.example.chatting.message.controller;

import com.example.chatting.message.service.SendMessageService;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SendMessageController {
    private final SendMessageService service;

    @MessageMapping("/send-message")
    @SendTo("/topic/receive-message")
    MessageVo send(Request request) {
        var requirement = request.toRequirement();
        return service.create(requirement);
    }

    record Request(
        long roomId,
        String userId,
        String content
    ) {
        public SendMessageService.Requirement toRequirement() {
            return new SendMessageService.Requirement(
                roomId,
                userId,
                content
            );
        }
    }
}
