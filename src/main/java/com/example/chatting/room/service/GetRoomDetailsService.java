package com.example.chatting.room.service;

import com.example.chatting.room.domain.ChattingRoom;
import com.example.chatting.room.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetRoomDetailsService {
    private final ChattingRoomRepository roomRepository;

    @Transactional(readOnly = true)
    public RoomDetails get(long roomId) {
        var room = roomRepository.findById(roomId).orElseThrow();

        return RoomDetails.from(room);
    }

    public record RoomDetails(
        long id,
        String title,
        boolean isPrivate,
        List<Participant> participants
    ) {
        public static RoomDetails from(ChattingRoom room) {
            return new RoomDetails(
                room.id(),
                room.title(),
                room.isPrivate(),
                room.participants().stream()
                    .map(participant -> new Participant(
                        participant.id(),
                        participant.name()
                    ))
                    .toList()
            );
        }

        public record Participant(
            long id,
            String name
        ) {
        }
    }
}
