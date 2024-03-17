package com.example.chatting.room.service;

import com.example.chatting.room.domain.ChattingParticipant;
import com.example.chatting.room.domain.ChattingRoom;
import com.example.chatting.room.repository.ChattingRoomRepository;
import com.example.chatting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateRoomService {
    private final ChattingRoomRepository chattingRoomRepository;
    private final UserRepository userRepository;

    public void create(Requirement requirement) {
        var user  = userRepository.findById(requirement.userId()).orElseThrow();
        var participant = new ChattingParticipant(user.id(), user.name());
        var chattingRoom = new ChattingRoom(
            requirement.title(),
            requirement.isPrivate(),
            List.of(participant)
        );

        chattingRoom.enter(user);
        chattingRoomRepository.save(chattingRoom);
    }

    public record Requirement(
        String userId,
        String title,
        boolean isPrivate
    ) {
    }
}
