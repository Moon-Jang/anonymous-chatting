package com.example.chatting.room.controller;

import com.example.chatting.room.service.EnterRoomService;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EnterRoomController {
    private final EnterRoomService service;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/enter")
    void enter(Request request) {
        var message = service.enter(request.roomId(), request.userId());

        message.ifPresent(m ->
            messagingTemplate.convertAndSend("/topic/receive-message", m)
        );
    }

    record Request(
        long roomId,
        String userId
    ) {
    }
}
