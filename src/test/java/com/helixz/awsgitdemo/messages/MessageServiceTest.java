package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createValidMessage_validResponse() {

        Message message = new Message();
        when(messageRepository.save(message)).thenReturn(message);

        ResponseEntity<Message> response = messageService.createMessage(message);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(message,response.getBody());

    }
    @Test
    void handle_null_message(){
        ResponseEntity<Message> response = messageService.createMessage(null);
        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    }

    @Test
    void test_message_sort_descending(){
        Message message =new Message();
        Message message1 =new Message();
        List<Message> messageList = Arrays.asList(message,message1);

        when(messageRepository.findAll(Sort.by(Sort.Direction.DESC,"createdDate"))).thenReturn(messageList);

        List<Message> response = messageService.getMessage();
        assertEquals(messageList,response);
        assertEquals(2,response.size());

    }




}