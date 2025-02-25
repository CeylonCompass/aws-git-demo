package com.helixz.awsgitdemo.users.dto;



public record UserCreateRequest (

        String firstName,
        String lastName,
        String email
){
}
