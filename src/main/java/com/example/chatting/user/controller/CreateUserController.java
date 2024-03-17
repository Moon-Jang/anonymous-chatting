package com.example.chatting.user.controller;

import com.example.chatting.common.web.ApiResponse;
import com.example.chatting.user.service.CreateUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class CreateUserController {
    private final CreateUserService service;

    @PostMapping
    ApiResponse<Void> create(@Valid @RequestBody Request request) {
        service.create(request.toRequirement());

        return ApiResponse.success();
    }

    record Request(
        @NotBlank(message = "아이디는 필수값입니다.")
        String id,
        @NotBlank(message = "이름은 필수값입니다.")
        String name
    ) {
        public CreateUserService.Requirement toRequirement() {
            return new CreateUserService.Requirement(
                id,
                name
            );
        }
    }
}
