package com.helixz.awsgitdemo.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UserCreateRequest(

        @NotBlank(message = "name required")
        String name,
        @NotBlank(message = "email required")
        String email

) {
}
