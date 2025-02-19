package com.helixz.awsgitdemo.messages;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    Message toMessageEntity(MessageCreateRequest request);

    MessageCreateResponse toMessageResponse(Message message);

    List<MessageCreateResponse> toMessageCreateResponse(List<Message> content);
}
