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
@RequiredArgsConstructor
@Transactional
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> getAllMessages() {
        return messageRepository.findAllByOrderByCreatedDateDesc();
    }

    public Message createMessage(String msg) {
        if (msg == null || msg.isBlank()) {
            return null;
        }
        Message message = new Message();
        message.setMessage(msg);
        message.setCreatedDate(LocalDateTime.now());
        return messageRepository.save(message);
    }
}
