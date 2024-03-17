package com.example.chatting.message.service;

import com.example.chatting.message.domain.ChattingMessage;
import com.example.chatting.message.repository.ChattingMessageRepository;
import com.example.chatting.room.repository.ChattingRoomRepository;
import com.example.chatting.message.vo.MessageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SendMessageService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final ChattingMessageRepository chattingMessageRepository;

    @Transactional
    public MessageVo create(Requirement requirement) {
        if (!chattingRoomRepository.existsById(requirement.roomId())) {
            throw new IllegalArgumentException("채팅방이 존재하지 않습니다.");
        }

        var message = createMessage(requirement);
        var savedMessage = chattingMessageRepository.save(message);

        return MessageVo.from(savedMessage);
    }

    private ChattingMessage createMessage(Requirement requirement) {
        return new ChattingMessage(
            requirement.roomId(),
            requirement.userId(),
            requirement.content(),
            ChattingMessage.MessageType.CHAT,
            ChattingMessage.ContentType.TEXT,
            ChattingMessage.SenderType.USER
        );
    }

    public record Requirement(
        Long roomId,
        String userId,
        String content
    ) {
    }

}
