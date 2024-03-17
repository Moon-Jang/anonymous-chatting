package com.example.chatting.user.service;

import com.example.chatting.user.domain.User;
import com.example.chatting.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserService {
    private final UserRepository userRepository;

    public void create(Requirement requirement) {
        if (userRepository.existsById(requirement.id())) {
            log.info("User already exists: {}", requirement.id());
            return;
        }

        var user = new User(
            requirement.id(),
            requirement.name()
        );

        userRepository.save(user);
    }

    public record Requirement(
        String id,
        String name
    ) {
    }
}
