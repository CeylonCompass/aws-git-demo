package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
   class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;
    @InjectMocks
    private MessageService messageService;

    @Test
    void saveMessageTest() {
        Message message = new Message();
        message.setId(1L);
        message.setMessage("Test One");
        message.setCreatedDate(LocalDateTime.now());
        message.setLastModifiedDate(LocalDateTime.now());

        when(messageRepository.save(message)).thenReturn(message);
        Message savedMessage = messageService.saveMessage(message);
        assertEquals(message, savedMessage, "The saved message should match the input message");
    }

    @Test
    void getAllByDescendingOrderTest() {
        Message message1 = new Message();
        message1.setId(1L);
        message1.setMessage("Test One");
        String dateString1 = "2025-02-18 15:27:13.799748";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime specificDateTime = LocalDateTime.parse(dateString1, formatter);
        message1.setCreatedDate(specificDateTime);
        message1.setLastModifiedDate(specificDateTime);

        Message message2 = new Message();
        message2.setId(2L);
        message2.setMessage("Test Two");
        String dateString2 = "2025-02-18 15:29:13.799748";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime specificDateTime2 = LocalDateTime.parse(dateString2, formatter2);
        message2.setCreatedDate(specificDateTime2);
        message2.setLastModifiedDate(specificDateTime2);

        when(messageRepository.findAll()).thenReturn(List.of(message1, message2));

        List<Message> result = messageService.getAllMessages();
        assertEquals("Test Two", result.get(0).getMessage());
        assertEquals("Test One", result.get(1).getMessage());

    }
}
