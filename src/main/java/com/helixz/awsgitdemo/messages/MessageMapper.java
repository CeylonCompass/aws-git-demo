package com.helixz.awsgitdemo.messages;

import com.helixz.awsgitdemo.users.UserDetailsEntity;
import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageDTO toDTO(Message message);

    Message toMsg(MessageDTO messageDTO);

    UserDetailsEntity toUsersEntity(UserCreateRequest userCreateRequest);

    UserCreateResponse toUserCreateResponse(UserDetailsEntity userDetailsEntity);

}
