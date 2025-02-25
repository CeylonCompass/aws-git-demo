package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(UserCreateRequest request);
    UserCreateRequest toUserResponse(User message);
    List<UserCreateResponse> toUserCreateResponse(List<User> content);
}
