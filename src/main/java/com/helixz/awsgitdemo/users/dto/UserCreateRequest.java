package com.helixz.awsgitdemo.users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateRequest {

    @NotBlank(message = "firstname not be blank")
    private String firstName;

    @NotBlank(message = "lastname not be blank")
    private String lastName;

    @NotBlank(message = "email not be blank")
    private String email;

    @NotBlank(message = "password not be blank")
    private String password;

    @NotBlank(message = "username not be blank")
    private String username;

}
