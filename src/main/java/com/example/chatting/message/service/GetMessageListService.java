package com.example.chatting.message.service;

import com.example.chatting.message.repository.ChattingMessageRepository;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetMessageListService {
    private final ChattingMessageRepository chattingMessageRepository;

    public List<MessageVo> get(long roomId) {
        return chattingMessageRepository.findAllByRoomId(roomId)
            .stream()
            .map(MessageVo::from)
            .toList();
    }
}
