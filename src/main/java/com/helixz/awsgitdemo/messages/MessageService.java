package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {


    private final MessageRepository messageRepository;

    public ResponseEntity<?> createMessage(String content) {
        if (content == null || content.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Collections.singletonMap("error", "Content must not be empty/null"));
        }

        Message message = new Message();
        message.setMessage(content);
        Message savedMessage = messageRepository.save(message);

        Map<String, Object> response = new HashMap<>();
        response.put("id", savedMessage.getId());
        response.put("content", savedMessage.getMessage());
        response.put("createdAt", savedMessage.getCreatedDate());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    public ResponseEntity<?> getMessage(){
        return  ResponseEntity.ok(messageRepository.findAllByOrderByCreatedDateDesc());
    }

}
