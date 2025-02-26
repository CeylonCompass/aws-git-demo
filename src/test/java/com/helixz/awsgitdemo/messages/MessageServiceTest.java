package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @Test
    void testCreateMessageIsValid() {
        Message message = new Message();
        message.setMessage("");

        ResponseStatusException exception = assertThrows(
                ResponseStatusException.class,
                () -> messageService.createMessage(message)
        );

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testRetrieveMessagesAreSorted() {
        LocalDateTime now = LocalDateTime.now();
        Message message1 = new Message();
        message1.setMessage("Message 1");
        message1.setCreatedDate(now.minusDays(1));

        Message message2 = new Message();
        message2.setMessage("Message 2");
        message2.setCreatedDate(now.minusDays(2));

        Message message3 = new Message();
        message3.setMessage("Message 3");
        message3.setCreatedDate(now.minusDays(3));

        List<Message> messageList = List.of(message1, message2, message3);

        when(messageRepository.findAll(Sort.by(Sort.Direction.DESC, "createdDate")))
                .thenReturn(messageList);

        List<Message> retrievedMessages = messageService.retrieveMessages();

        Assertions.assertFalse(retrievedMessages.isEmpty(), "Retrieved list should not be empty");

        for (int i = 0; i < retrievedMessages.size() - 1; i++) {
            LocalDateTime current = retrievedMessages.get(i).getCreatedDate();
            LocalDateTime next = retrievedMessages.get(i + 1).getCreatedDate();
            Assertions.assertTrue(current.isAfter(next) || current.isEqual(next),
                    "Messages should be sorted in descending order");
        }
    }
}



