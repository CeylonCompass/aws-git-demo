package com.helixz.awsgitdemo.users.dto;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCreateResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate createdDate
) {
}
