package com.example.chatting.message.controller;

import com.example.chatting.message.service.GetMessageListService;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/messages")
@RequiredArgsConstructor
public class GetMessageListController {
    private final GetMessageListService service;

    @GetMapping
    List<MessageVo> get(@PathVariable Long roomId) {
        return service.get(roomId);
    }
}
