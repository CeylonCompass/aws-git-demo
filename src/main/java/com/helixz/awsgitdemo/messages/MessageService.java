package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    private final MessageRepository messageRepository;

    public ResponseEntity<Message> createMessage(Message message) {
        if (message ==null){
            log.error("Message is null");
            return ResponseEntity.badRequest().build();
        }
        Message saveMessage = messageRepository.save(message);

        return new ResponseEntity<>(saveMessage,HttpStatus.CREATED);



    }

    public List<Message> getMessage(){
        return messageRepository.findAll(Sort.by(Sort.Direction.DESC,"createdDate"));
    }




}
