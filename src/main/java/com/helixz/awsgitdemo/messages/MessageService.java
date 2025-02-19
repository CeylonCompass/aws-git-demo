package com.helixz.awsgitdemo.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.helixz.awsgitdemo.exception.impl.ValidationFailedException;
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
import java.util.stream.Collectors;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;


    public ResponseEntity<MessageDTO> createMessage(String content) {
        if (content == null || content.trim().isEmpty())
            throw new ValidationFailedException("Content must not be empty/null");

        Message message = new Message();
        message.setMessage(content);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapper.convertValue(messageRepository.save(message),MessageDTO.class));

    }


    public ResponseEntity<List<MessageDTO>> getMessages() {
        List<MessageDTO> messageDTOs = messageRepository.findAllByOrderByCreatedDateDesc()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDTOs);
    }

    private MessageDTO convertToDto(Message message) {
        return new MessageDTO(message.getId(), message.getMessage(), message.getCreatedDate());
    }

}
