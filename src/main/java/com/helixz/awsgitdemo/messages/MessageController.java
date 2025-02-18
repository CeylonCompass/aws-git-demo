package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MessageController {

   private final MessageService messageService;

    @PostMapping("/messages")
    public ResponseEntity<?> createMessage(@RequestBody Map<String, String>  content) {
        String messageContent = content.get("content");
        return messageService.createMessage(messageContent);
    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages () {
        return messageService.getMessage();
    }
}
