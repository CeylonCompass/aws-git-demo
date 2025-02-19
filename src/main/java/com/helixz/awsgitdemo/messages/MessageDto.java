package com.helixz.awsgitdemo.messages;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MessageDto(
        Long id,
        String content,
        LocalDateTime createdAt
) {
}
