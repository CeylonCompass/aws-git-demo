package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class MessageController {
    private final MessageService service;
    @GetMapping("messages")
    public ResponseEntity<Object> getAllMessages() {
        return service.getAllMessages();
    }

    @PostMapping("message")
    public ResponseEntity<Object> persist(@RequestBody Message message) {
        return service.messagePersist(message);
    }
}
