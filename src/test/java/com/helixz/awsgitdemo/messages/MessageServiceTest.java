package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.*;

class MessageServiceTest {

    MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    MessageService messageService = new MessageService(messageRepository);

    @Test
    void createMessage_ValidMessage_ReturnsOkResponse() {
        Message validMessage = new Message();
        validMessage.setMessage("Test message");
        ResponseEntity<Object> response = messageService.createMessage(validMessage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createMessage_NullMessage_ReturnsBadRequestResponse() {
        Message nullMessage = new Message();
        ResponseEntity<Object> response = messageService.createMessage(nullMessage);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMessage_EmptyMessage_ReturnsBadRequestResponse() {
        Message emptyMessage = new Message();
        emptyMessage.setMessage("");
        ResponseEntity<Object> response = messageService.createMessage(emptyMessage);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllMessages_ReturnsSuccessResponse() {
        ResponseEntity<Object> response = messageService.getAllMessages();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}