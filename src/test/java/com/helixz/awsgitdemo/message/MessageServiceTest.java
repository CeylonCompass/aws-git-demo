package com.helixz.awsgitdemo.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.helixz.awsgitdemo.exception.impl.ValidationFailedException;
import com.helixz.awsgitdemo.messages.Message;
import com.helixz.awsgitdemo.messages.MessageDTO;
import com.helixz.awsgitdemo.messages.MessageRepository;
import com.helixz.awsgitdemo.messages.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    MessageRepository messageRepository;
    ObjectMapper objectMapper;
    MessageService messageService;

    @BeforeEach
    void setUp() {
        messageRepository = Mockito.mock(MessageRepository.class);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        messageService = new MessageService(messageRepository, objectMapper);
    }


    @Test
    void createMessage_ValidMessage() {
        String content = "Test message";
        Message mockMessage = new Message();
        mockMessage.setId(1L);
        mockMessage.setMessage(content);
        when(messageRepository.save(any(Message.class))).thenReturn(mockMessage);
        ResponseEntity<?> response = messageService.createMessage(content);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void createMessage_NullMessage() {
        Exception exception = assertThrows(ValidationFailedException.class, () -> {
            messageService.createMessage(null);
        });
        assertEquals("Content must not be empty/null", exception.getMessage());
    }

    @Test
    void createMessage_EmptyMessage() {
        Exception exception = assertThrows(ValidationFailedException.class, () -> {
            messageService.createMessage("");
        });
        assertEquals("Content must not be empty/null", exception.getMessage());
    }

    @Test
    void getMessages_ShouldReturnEmptyList() {
        when(messageRepository.findAllByOrderByCreatedDateDesc()).thenReturn(Collections.emptyList());
        ResponseEntity<List<MessageDTO>> response = messageService.getMessages();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(0, response.getBody().size());
    }
}