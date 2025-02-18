package com.helixz.awsgitdemo.messages;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class MessageServiceTest {

    @Test
    void testCreateMessage() {
        Message message = new Message();
        message.setMessage("this is a test");
        message.setId(1L);
        message.setCreatedDate(LocalDateTime.now());

        assert message.getId() == 1L;
        assert "this is a test".equals(message.getMessage());
        assert message.getCreatedDate() != null;
    }

    @Test
    void testMessageSorting() {
        Message message1 = new Message();
        message1.setMessage("this is the test message one");
        message1.setId(1L);
        message1.setCreatedDate(LocalDateTime.now().minusHours(1));

        Message message2 = new Message();
        message2.setMessage("this is the test message two");
        message2.setId(2L);
        message2.setCreatedDate(LocalDateTime.now());

        assert message2.getCreatedDate().isAfter(message1.getCreatedDate());
    }
}