package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        return messageService.createMessage(message);

    }

    @GetMapping("/messages")
    public List<Message> getMessage(){
        return messageService.getMessage();
    }
}
