package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("messages")
    public ResponseEntity<Object> getAllMessages() {
        return messageService.getAllMessages();
    }


    @PostMapping("message")
    public ResponseEntity<Object> persist(@RequestBody Message message) {
        return messageService.createMessage(message);
    }
}

