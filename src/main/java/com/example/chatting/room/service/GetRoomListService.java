package com.example.chatting.room.service;

import com.example.chatting.room.repository.ChattingRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GetRoomListService {
    private final ChattingRoomRepository roomRepository;

    public List<RoomSummary> get() {
        var rooms = roomRepository.findAll();

        return rooms.stream()
            .map(room -> new RoomSummary(
                room.id(),
                room.title(),
                room.isPrivate()
            ))
            .collect(Collectors.toList());
    }

    public record RoomSummary(
        long id,
        String title,
        boolean isPrivate
    ) {
    }
}
