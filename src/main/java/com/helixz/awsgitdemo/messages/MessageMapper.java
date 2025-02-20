package com.helixz.awsgitdemo.messages;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "content", target = "message")
    @Mapping(source = "createdAt", target = "createdDate")
    @Mapping(target = "lastModifiedDate", ignore = true)
    Message messageDtoToMessage(MessageDto dto);

    @InheritInverseConfiguration
    MessageDto messageToMessageDto(Message message);
}
