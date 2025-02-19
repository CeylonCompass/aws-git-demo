package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Chamith Kodikara
 */
@RestController
@RequestMapping("/api/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDTO> createMessage(@RequestBody Map<String, String>  content) {
        String messageContent = content.get("content");
        return messageService.createMessage(messageContent);
    }

    @GetMapping
    public ResponseEntity<List<MessageDTO>> getMessages () {
        return messageService.getMessages();
    }
}
