package com.helixz.awsgitdemo.users.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserCreateResponse(

        String firstName,
        String lastName,
        String email,
        LocalDateTime createdDate
) {
}
