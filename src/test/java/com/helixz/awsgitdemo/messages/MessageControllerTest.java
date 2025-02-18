package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageControllerTest {

    MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    MessageService messageService = new MessageService(messageRepository);
    MessageController controller = new MessageController(messageService);

    @Test
    void createMessage_ValidMessage_ReturnsOkResponse() {
        Message validMessage = new Message();
        validMessage.setMessage("Test message");
        ResponseEntity<Object> response = controller.persist(validMessage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createMessage_NullMessage_ReturnsBadRequestResponse() {
        Message nullMessage = new Message();
        ResponseEntity<Object> response = controller.persist(nullMessage);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void createMessage_EmptyMessage_ReturnsBadRequestResponse() {
        Message emptyMessage = new Message();
        emptyMessage.setMessage("");
        ResponseEntity<Object> response = controller.persist(emptyMessage);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getAllMessages_ReturnsSuccessResponse() {
        ResponseEntity<Object> response = controller.getAllMessages();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
