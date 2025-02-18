package com.helixz.awsgitdemo.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {
    final MessageRepository messageRepository;
    final ObjectMapper mapper;

    public Message createMessage(Message message) {
        if (message.getMessage()==null || message.getMessage().trim().isEmpty()){
            log.error("Invalid input: Message cannot be empty");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Message cannot be empty");
        } else {
            log.info("Valid input");
            return messageRepository.save(message);
        }
    }

    public List<Message> retrieveMessages() {
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC,"createdDate"));
    }
}
