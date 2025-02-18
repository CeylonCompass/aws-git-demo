package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;


    @PostMapping("/saveMessage")
    public HashMap<String, Object> createMessage(@RequestBody Message message) {
        return messageService.createMessage(message);
    }

    @GetMapping("/getMassages")
    public List<Message> getAllMessages(){
        return messageService.getAllMessages();
    }
}
