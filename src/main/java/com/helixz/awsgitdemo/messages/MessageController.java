package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
public class MessageController {
    final MessageService messageService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Message createMessage(@RequestBody Message message){
        return messageService.createMessage(message);
    }

    @GetMapping("/retrieve")
    public List<Message> retrieveMessages(){
        return messageService.retrieveMessages();
    }
}
