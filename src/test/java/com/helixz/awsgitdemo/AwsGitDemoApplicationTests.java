package com.helixz.awsgitdemo;

import com.helixz.awsgitdemo.messages.Message;
import com.helixz.awsgitdemo.messages.MessageRepository;
import com.helixz.awsgitdemo.messages.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest()
class AwsGitDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
    MessageService messageService = new MessageService(messageRepository);

    Message messages =new Message();

    @Test
    void test_createMessage_whenNull(){
        messages.setMessage(null);
        HashMap<String, Object> message = messageService.createMessage(messages);
        Assertions.assertEquals("201",message.get("status"));

    }

    @Test
    void test_createMessage_whenEmpty(){
        messages.setMessage("");
        HashMap<String, Object> message = messageService.createMessage(messages);
        Assertions.assertEquals("201",message.get("status"));

    }

    @Test
    void test_createMessage_whenSucceffllySaved(){
        messages.setMessage("Hello How are you !");
        HashMap<String, Object> message = messageService.createMessage(messages);
        Assertions.assertEquals("200",message.get("status"));

    }

}
