package com.helixz.awsgitdemo.messages.dto;

import lombok.Builder;

import java.time.LocalDateTime;


public record MessageCreateResponse(

        Long id,
        String message,
        LocalDateTime createdDate


) {
}
