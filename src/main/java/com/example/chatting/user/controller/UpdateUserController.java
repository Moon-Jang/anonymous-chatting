package com.example.chatting.user.controller;

import com.example.chatting.common.web.ApiResponse;
import com.example.chatting.user.service.CreateUserService;
import com.example.chatting.user.service.UpdateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/users/{userId}")
@RequiredArgsConstructor
public class UpdateUserController {
    private final UpdateUserService service;

    @PutMapping
    ApiResponse<Void> update(@PathVariable String userId,
                             @Valid @RequestBody Request request) {
        service.update(request.toRequirement(userId));

        return ApiResponse.success();
    }

    record Request(
        @NotBlank(message = "이름은 필수값입니다.")
        String name
    ) {
        public UpdateUserService.Requirement toRequirement(String userId) {
            return new UpdateUserService.Requirement(
                userId,
                name
            );
        }
    }
}
