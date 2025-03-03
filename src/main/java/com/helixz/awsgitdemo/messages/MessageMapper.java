package com.helixz.awsgitdemo.messages;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;
import com.helixz.awsgitdemo.messages.dto.MessageGetResponse;

/**
 * @author Chamith Kodikara
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    Message toMessageEntity(MessageCreateRequest request);

    MessageCreateResponse toMessageResponse(Message message);

    List<MessageCreateResponse> toMessageCreateResponse(List<Message> content);

    List<MessageGetResponse> toMessageGetResponse(List<Message> content);

}
