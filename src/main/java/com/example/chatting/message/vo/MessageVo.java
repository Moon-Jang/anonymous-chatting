package com.example.chatting.message.vo;

import com.example.chatting.message.domain.ChattingMessage;

import java.time.LocalDateTime;

public record MessageVo(
    long id,
    long roomId,
    String userId,
    String content,
    ChattingMessage.MessageType messageType,
    ChattingMessage.ContentType contentType,
    ChattingMessage.SenderType senderType,
    LocalDateTime createdAt
) {
    public static MessageVo from(ChattingMessage message) {
        return new MessageVo(
            message.id(),
            message.roomId(),
            message.userId(),
            message.content(),
            message.messageType(),
            message.contentType(),
            message.senderType(),
            message.createdAt()
        );
    }
}
