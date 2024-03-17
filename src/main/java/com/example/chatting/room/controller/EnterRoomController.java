package com.example.chatting.room.controller;

import com.example.chatting.room.service.EnterRoomService;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class EnterRoomController {
    private final EnterRoomService service;

    @MessageMapping("/enter")
    @SendTo("/topic/receive-message")
    MessageVo enter(Request request) {
        return service.enter(request.roomId(), request.userId());
    }

    record Request(
        long roomId,
        String userId
    ) {
    }
}
