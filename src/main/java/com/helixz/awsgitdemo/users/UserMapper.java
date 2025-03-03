package com.helixz.awsgitdemo.users;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(UserCreateRequest request);
    UserCreateRequest toUserResponse(User message);
    List<UserCreateResponse> toUserCreateResponse(List<User> content);
}
