package com.helixz.awsgitdemo.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCreateResponse {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String username;

}
