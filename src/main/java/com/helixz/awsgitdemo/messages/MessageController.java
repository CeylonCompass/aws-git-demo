package com.helixz.awsgitdemo.messages;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
public class MessageController {


    private final MessageService messageService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createMessage(@Valid @RequestBody Message message) {
        messageService.createMessage(message);
        return "message saved successfully!";
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }




}
