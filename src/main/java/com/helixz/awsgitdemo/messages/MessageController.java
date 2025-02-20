package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @GetMapping("messages")
    public ResponseEntity<List<MessageDto>> getAllMessages() {
        return messageService.getAllMessages();
    }


    @PostMapping("messages")
    public ResponseEntity<MessageDto> persist(@RequestBody MessageDto message) {
        return messageService.createMessage(message);
    }
}

