package com.helixz.awsgitdemo.messages;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;
import com.helixz.awsgitdemo.messages.dto.MessageGetResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;


    @Operation(summary = "Create new message")
    @ApiResponse(responseCode = "201", description = "Successfully created new message")
    @ApiResponse(responseCode = "400", description = "if request body is invalid")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageCreateResponse> createNewMessage(@RequestBody @Validated MessageCreateRequest request) {
        Message message = messageService.createMessage(messageMapper.toMessageEntity(request));
        return new ResponseEntity<>(messageMapper.toMessageResponse(message), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MessageGetResponse>> getMessages () {
        return messageService.getMessages();
    }
}
