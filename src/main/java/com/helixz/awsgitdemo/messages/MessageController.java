package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@CrossOrigin
@RequestMapping("api/messages")
@RequiredArgsConstructor
@RestController
public class MessageController {

    private final MessageService messageService;

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Message> createMessage(@RequestBody MessageDto content) {
        if (content.getContent() == null || content.getContent().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Message savedMessage = messageService.createMessage(content.getContent());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMessage);
    }
}
