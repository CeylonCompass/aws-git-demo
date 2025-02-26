package com.helixz.awsgitdemo.messages;

import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;

import com.helixz.awsgitdemo.messages.dto.MessageSearchResponse;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @Operation(summary = "Create new message")
    @ApiResponse(responseCode = "201", description = "Successfully created new message")
    @ApiResponse(responseCode = "400", description = "if request body is invalid")
    @PostMapping("/api/messages")
    public ResponseEntity<MessageCreateResponse> saveMessage(@RequestBody @Validated MessageCreateRequest request) {
//        return message.getMessage() == null || message.getMessage().isEmpty() ?
        //            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message can't be empty") :
        ///              ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(message));

        Message message = messageService.saveMessage(messageMapper.toMessageEntity(request));
        return new ResponseEntity<>(messageMapper.toMessageResponse(message), HttpStatus.CREATED);

    }

    @GetMapping("GET/api/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping(value = "/search-messages", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageSearchResponse> searchMessages(
            @Parameter(description = "Search term to filter messages")
            @RequestParam(name = "searchTerm", required = false)
            String searchTerm,
            @ParameterObject @SortDefault.SortDefaults({
                    @SortDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC)
            })
            Pageable pageable) {

        Page<Message> messages = messageService.searchMessages(searchTerm, pageable);
        List<MessageCreateResponse> content = messageMapper.toMessageCreateResponse(messages.getContent());
        MessageSearchResponse response = MessageSearchResponse.builder()
                .contents(content)
                .paginationData(MessageSearchResponse.PaginationData.builder()
                        .totalElements((int) messages.getTotalElements())
                        .totalPages(messages.getTotalPages())
                        .pageSize(pageable.getPageSize())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
