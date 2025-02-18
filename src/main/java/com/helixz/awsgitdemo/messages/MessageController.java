package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("POST/api/messages")
    public ResponseEntity<Object> saveMessage(@RequestBody Message message) {
        return message.getMessage() == null || message.getMessage().isEmpty() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Message can't be empty") :
                ResponseEntity.status(HttpStatus.CREATED).body(messageService.saveMessage(message));
    }

    @GetMapping("GET/api/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
}
