package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    public ResponseEntity<Object> createMessage(Message message) {
        if (message.getMessage() == null || message.getMessage().isBlank()) {
            HashMap<String, String> body = new HashMap<>();
            body.put("error", "content must not be empty/null");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        } else {
            return ResponseEntity.ok().body(
                    messageRepository.save(message)
            );
        }
    }

    public ResponseEntity<Object> getAllMessages() {
        return new ResponseEntity<>(messageRepository.findAllByOrderByCreatedDateDesc(), HttpStatus.OK);
    }
}
