package com.helixz.awsgitdemo.message;

import com.helixz.awsgitdemo.messages.Message;
import com.helixz.awsgitdemo.messages.MessageRepository;
import com.helixz.awsgitdemo.messages.MessageService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


class MessageServiceTest {

    MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    MessageService messageService = new MessageService(messageRepository);

    @Test
    void createMessage_ValidMessage() {
        String content = "Test message";
        Message mockMessage = new Message();
        mockMessage.setId(1L);
        mockMessage.setMessage(content);
        mockMessage.setCreatedDate(LocalDateTime.now());
        when(messageRepository.save(any(Message.class))).thenReturn(mockMessage);
        ResponseEntity<?> response = messageService.createMessage(content);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void createMessage_NullMessage() {
        String content = null;
        ResponseEntity<?> response = messageService.createMessage(content);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    void createMessage_EmptyMessage() {
        String content = "";
        ResponseEntity<?> response = messageService.createMessage(content);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void getMessageTest() {
        when(messageRepository.findAllByOrderByCreatedDateDesc()).thenReturn(Collections.emptyList());
        ResponseEntity<?> response = messageService.getMessage();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, ((List<?>) response.getBody()).size()); // Ensure the response contains an empty list
    }
}
