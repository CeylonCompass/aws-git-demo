package com.helixz.awsgitdemo.messages;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helixz.awsgitdemo.exception.impl.ValidationFailedException;
import com.helixz.awsgitdemo.messages.dto.MessageGetResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public Message createMessage(Message message) {
        if (message.getMessage() == null || message.getMessage().trim().isEmpty())
            throw new ValidationFailedException("Content must not be empty/null");
        return messageRepository.save(message);
    }

    public ResponseEntity<List<MessageGetResponse>> getMessages() {
        List<Message> allByOrderByCreatedDateDesc = messageRepository.findAllByOrderByCreatedDateDesc();
        return  new ResponseEntity<>(
                messageMapper.toMessageGetResponse(allByOrderByCreatedDateDesc),
                HttpStatus.CREATED);
    }

}
