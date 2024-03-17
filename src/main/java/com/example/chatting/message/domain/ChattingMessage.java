package com.example.chatting.message.domain;

import com.example.chatting.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Getter
@Accessors(fluent = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChattingMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chatting_room_id", nullable = false)
    private long roomId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContentType contentType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SenderType senderType;

    public ChattingMessage(long roomId,
                           String userId,
                           String content,
                           MessageType messageType,
                           ContentType contentType,
                           SenderType senderType) {
        this.roomId = roomId;
        this.userId = userId;
        this.content = content;
        this.messageType = messageType;
        this.contentType = contentType;
        this.senderType = senderType;
    }

    public enum MessageType{
        ENTER,
        CHAT,
        LEAVE,
        NOTICE
    }

    public enum ContentType{
        TEXT,
        FILE
    }

    public enum SenderType{
        USER,
        ADMIN,
        SYSTEM
    }
}
