package com.helixz.awsgitdemo.messages;
import com.helixz.awsgitdemo.messages.dto.MessageSearchResponse;
import io.swagger.v3.oas.annotations.Parameter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


import java.util.List;


import com.helixz.awsgitdemo.messages.dto.MessageCreateRequest;
import com.helixz.awsgitdemo.messages.dto.MessageCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;



/**
 * @author Chamith Kodikara
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/messages")
@Tag(name = "Message controller", description = "This is used to handle all the APIs related to Messages")
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

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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
                        .page(pageable.getPageNumber())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
