package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private  final MessageRepository messageRepository;

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        List<Message> messageList =new ArrayList<>( messageRepository.findAll());
        messageList.sort(Comparator.comparing(Message::getCreatedDate).reversed());
        return messageList;
    }
    public Page<Message> searchMessages(String searchTerm, Pageable pageable) {
        Page<Message> messages;
        if (StringUtils.isBlank(searchTerm)) {
            messages = messageRepository.findAll(pageable);
        } else {
            searchTerm = new StringBuilder().append("%").append(searchTerm).append("%").toString();
            messages = messageRepository.findAll(searchTerm, pageable);
        }
        return messages;
    }
}
