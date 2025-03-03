package com.helixz.awsgitdemo.messages.dto;

import java.time.LocalDateTime;

import lombok.Builder;

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
