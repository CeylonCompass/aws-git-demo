package com.helixz.awsgitdemo.users.dto;

public record UserCreateResponse(

        Long id,
        String name,
        String email
) {
}
