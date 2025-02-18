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

    private final MessageRepository repository;

    public List<Message> getAllMessages() {
        return repository.findAllByOrderByCreatedDateDesc();
    }

    public Message createMessage(String content) {
        if (content == null || content.isBlank())
            return null;
        Message newMessage = new Message();
        newMessage.setMessage(content);
        newMessage.setCreatedDate(LocalDateTime.now());
        return repository.save(newMessage);
    }
}
