package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    User toUserEntity(UserCreateRequest request);

    UserCreateResponse toUserResponse(User user);

    List<UserCreateResponse> toUserCreateResponses(List<User> userList);
}
