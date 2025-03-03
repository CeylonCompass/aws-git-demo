package com.helixz.awsgitdemo.users.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record UserCreateResponse (

        String firstName ,
        String lastName,
        String email,
        LocalDateTime createdDate
){
}
