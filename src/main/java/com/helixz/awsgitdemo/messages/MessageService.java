package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MessageService {

   private final MessageRepository messageRepository;

    public HashMap<String, Object> createMessage(Message message) {
        HashMap<String, Object> responseMap = new HashMap<>();
        if (message.getMessage() == null || message.getMessage().trim().isEmpty()) {
            responseMap.put("status", "201");
            responseMap.put("message", "Message cannot be empty or Null");
            return responseMap;
        }

        try {
            Message savedMessage = messageRepository.save(message);
            responseMap.put("status", "200");
            responseMap.put("message", "Successfully saved");
            responseMap.put("data", savedMessage);
        } catch (Exception e) {
            log.info("Error saving message: {}", e.getMessage());
            responseMap.put("status", "error");
            responseMap.put("message", "Failed to save message" + e.getMessage());
        }
        return responseMap;
    }

    public List getAllMessages() {
      return   messageRepository.findAllByOrderByCreatedDateDesc();
    }
}
