package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessage() {
        return service.getAllMessages();
    }

    @PostMapping
    public ResponseEntity<Object> createMessage(@RequestBody MessageRequest request) {
        Message message = service.createMessage(request.getContent());
        return (message == null) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request body") : ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
}
