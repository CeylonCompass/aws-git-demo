package com.helixz.awsgitdemo.messages.dto;

import lombok.Builder;

import java.time.LocalDateTime;

/**
 * @author Chamith Kodikara
 */
@Builder
public record MessageGetResponse(

        Long id,
        String message,
        LocalDateTime createdDate
) {
}
