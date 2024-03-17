package com.example.chatting.room.service;

import com.example.chatting.message.domain.ChattingMessage;
import com.example.chatting.message.repository.ChattingMessageRepository;
import com.example.chatting.room.repository.ChattingRoomRepository;
import com.example.chatting.message.vo.MessageVo;
import com.example.chatting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnterRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingMessageRepository chattingMessageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageVo enter(long roomId,
                           String userId) {
        var room = chattingRoomRepository.findById(roomId).orElseThrow();
        var user = userRepository.findById(userId).orElseThrow();
        room.enter(user.id());
        chattingRoomRepository.save(room);

        var message = new ChattingMessage(
            roomId,
            "SYSTEM",
            user.name() + "님이 입장하셨습니다.",
            ChattingMessage.MessageType.TEXT,
            ChattingMessage.SenderType.SYSTEM
        );
        var savedMessage = chattingMessageRepository.save(message);

        return MessageVo.from(savedMessage);
    }
}
