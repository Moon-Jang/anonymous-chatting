package com.example.chatting.config;

import com.example.chatting.common.web.CommonObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {

    @Primary
    @Bean
    public ObjectMapper objectMapper() {
        return new CommonObjectMapper();
    }
}
