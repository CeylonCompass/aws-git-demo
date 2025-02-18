package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/messages")
public class MessageController {

    private final MessageService service;

    @PostMapping
    public ResponseEntity<Object> createMessage(@RequestBody RequestMessage request) {
        Message message = service.createMessage(request.getContent());
        return (message == null) ? ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid .") : ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Message> getAllMessage() {
        return service.getAllMessages();
    }
}
