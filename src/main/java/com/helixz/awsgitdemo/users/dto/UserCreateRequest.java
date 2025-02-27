package com.helixz.awsgitdemo.users.dto;

import lombok.Builder;

@Builder
public record UserCreateRequest(

            String firstName,
            String lastName,
            String email
) {
}
