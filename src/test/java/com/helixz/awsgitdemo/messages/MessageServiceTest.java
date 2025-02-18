package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MessageServiceTest {
    @Mock
    private MessageRepository repository;

    @InjectMocks
    private MessageService service;

    @Test
    void test_getAllMessages() {
        List<Message> mockMessages = new ArrayList<>();
        List<Message> output = repository.findAllByOrderByCreatedDateDesc();
        when(output).thenReturn(mockMessages);
        assertEquals(output, service.getAllMessages());
        verify(repository, times(1)).findAllByOrderByCreatedDateDesc();
    }

    @Test
    void test_createMessage_whenNull() {
        assertNull(service.createMessage(null), "Expected message : Null");
    }

    @Test
    void test_createMessage_whenEmpty() {
        assertNull(service.createMessage(""), "Expected message : Null");
    }

    @Test
    void test_createMessage_whenBlank() {
        assertNull(service.createMessage("     "), "Expected message : Null");
    }

    @Test
    void test_createMessage_successful() {
        Message mockMessage = new Message();
        mockMessage.setMessage("Test message!");

        when(repository.save(any(Message.class))).thenReturn(mockMessage);

        assertEquals(mockMessage.getMessage(), service.createMessage("Test message!").getMessage());

        verify(repository, times(1)).save(any(Message.class));
    }
}
