package com.helixz.awsgitdemo.users.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginResponse {

    private String bearerToken;
}
