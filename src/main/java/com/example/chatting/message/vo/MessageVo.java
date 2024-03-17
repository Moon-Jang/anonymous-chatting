package com.example.chatting.message.vo;

import com.example.chatting.message.domain.ChattingMessage;

public record MessageVo(
    long id,
    long roomId,
    String userId,
    String content,
    ChattingMessage.MessageType messageType,
    ChattingMessage.ContentType contentType,
    ChattingMessage.SenderType senderType
) {
    public static MessageVo from(ChattingMessage message) {
        return new MessageVo(
            message.id(),
            message.roomId(),
            message.userId(),
            message.content(),
            message.messageType(),
            message.contentType(),
            message.senderType()
        );
    }
}
