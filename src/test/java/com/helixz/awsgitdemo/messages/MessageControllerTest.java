package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessageControllerTest {

    MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    MessageMapper messageMapper = Mockito.mock(MessageMapper.class);
    MessageService messageService = new MessageService(messageRepository, messageMapper);
    MessageController controller = new MessageController(messageService);

    @Test
    void createMessage_ValidMessage_ReturnsOkResponse() {
        MessageDto validMessage = new MessageDto.MessageDtoBuilder().content("Valid Message").build();
        ResponseEntity<MessageDto> response = controller.persist(validMessage);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void createMessage_NullMessage_ReturnsBadRequestResponse() {
        MessageDto nullMessage = new MessageDto.MessageDtoBuilder().build();
        assertThrows(EmptyOrNullBodyException.class, () -> controller.persist(nullMessage));
    }

    @Test
    void createMessage_EmptyMessage_ReturnsBadRequestResponse() {
        MessageDto emptyMessage = new MessageDto.MessageDtoBuilder().content("").build();
        assertThrows(EmptyOrNullBodyException.class, () -> controller.persist(emptyMessage));
    }

    @Test
    void getAllMessages_ReturnsSuccessResponse() {
        ResponseEntity<List<MessageDto>> response = controller.getAllMessages();
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
