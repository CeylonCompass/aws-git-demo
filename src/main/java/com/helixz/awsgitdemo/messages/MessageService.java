package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository repository;
    public ResponseEntity<Object> getAllMessages() {
        List<Message> all = repository.findAll();
        if (all.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    public ResponseEntity<Object> messagePersist(Message message) {
        if (message != null && !message.getMessage().isEmpty()){
            return ResponseEntity.ok(repository.save(message));
        }
        return ResponseEntity.badRequest().body("Content must not be empty");
    }
}
