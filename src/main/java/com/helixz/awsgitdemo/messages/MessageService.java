package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {


    private final MessageRepository messageRepository;

    public void createMessage(Message message) {
        message.setCreatedDate(LocalDateTime.now());
        message.setLastModifiedDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByCreatedDateDesc();
    }
}
