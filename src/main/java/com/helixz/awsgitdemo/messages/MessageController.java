package com.helixz.awsgitdemo.messages;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Object> createMessage(@Valid @RequestBody MessageDTO messageDTO) {
        if (messageDTO.getMessage() == null || messageDTO.getMessage().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The message content cannot be null or empty.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.createMessage(messageDTO));
    }

    @GetMapping
    public ResponseEntity<Object> getMessages(
            @RequestParam(defaultValue = "createdDate") String sortBy,
            @RequestParam(defaultValue = "DESC") String sortDirection,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.status(HttpStatus.FOUND).body(
                messageService.getMessagesSorted(sortBy, sortDirection, page, size));
    }
}