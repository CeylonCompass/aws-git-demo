package com.helixz.awsgitdemo.messages;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring" ,unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    Message toMessageEntity(MessageCreateRequest request);

    MessageCreateResponse toMessageResponse(Message message);

    List<MessageCreateResponse> toMessageCreateResponse(List<Message> content);

}
