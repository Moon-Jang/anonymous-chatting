package com.example.chatting.message.repository;

import com.example.chatting.message.domain.ChattingMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChattingMessageRepository extends JpaRepository<ChattingMessage, Long> {

    List<ChattingMessage> findAllByRoomId(long roomId);
}
