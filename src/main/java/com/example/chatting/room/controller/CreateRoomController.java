package com.example.chatting.room.controller;

import com.example.chatting.common.web.ApiResponse;
import com.example.chatting.room.service.CreateRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class CreateRoomController {
    private final CreateRoomService service;

    @PostMapping
    ApiResponse<Void> create(Request request) {
        service.create(request.toRequirement());

        return ApiResponse.success();
    }

    record Request(
        String userId,
        String title,
        boolean isPrivate
    ) {
        public CreateRoomService.Requirement toRequirement() {
            return new CreateRoomService.Requirement(
                userId,
                title,
                isPrivate
            );
        }
    }
}
