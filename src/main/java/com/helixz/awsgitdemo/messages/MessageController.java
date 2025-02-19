package com.helixz.awsgitdemo.messages;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;


    @PostMapping
    public ResponseEntity<MessageCreateResponse> createNewMessage(@RequestBody @Valid MessageCreateRequest request) {
        Message message = messageService.createMessage(messageMapper.toMessageEntity(request));
        return new ResponseEntity<>(messageMapper.toMessageResponse(message), HttpStatus.CREATED);
    }
}
