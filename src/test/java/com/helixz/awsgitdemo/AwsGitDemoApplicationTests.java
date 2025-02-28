package com.helixz.awsgitdemo;

import com.helixz.awsgitdemo.messages.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class AwsGitDemoApplicationTests {
    @Autowired
    MessageService messageService;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    MessageController messageController;

    ArrayList<Message> messageArrayList = new ArrayList<>();
//    Message message=new Message();
//
//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    void test_saveMessage(){
//        MessageDTO serviceMessage = messageService.createMessage("New Message");
//        MessageDTO savedMsg = messageRepository.save(serviceMessage);
//        Assertions.assertNotNull(serviceMessage);
//        Assertions.assertFalse(serviceMessage.getMessage().isEmpty());
//        Assertions.assertEquals("New Message",serviceMessage.getMessage());
//        Assertions.assertEquals(savedMsg.getMessage(),serviceMessage.getMessage());
//    }
//
//    @Test
//    void test_getMessages(){
//        message.setId(1L);
//        message.setMessage("abc");
//        message.setCreatedDate(LocalDateTime.parse("2024-12-04T00:11:43.972889"));
//        message.setLastModifiedDate(LocalDateTime.parse("2024-12-04T00:11:43.972889"));
//        messageArrayList.add(message);
//        Assertions.assertTrue(messageArrayList.contains(message));
//        Assertions.assertEquals("abc",messageArrayList.getFirst().getMessage());
//    }
//
//    @Test
//    void test_postRequest(){
//        message.setId(1L);
//        message.setMessage("abc");
//        message.setCreatedDate(LocalDateTime.parse("2024-12-04T00:11:43.972889"));
//        message.setLastModifiedDate(LocalDateTime.parse("2024-12-04T00:11:43.972889"));
//        Message message1=new Message();
//        message1.setMessage("");
//        ResponseEntity<Message> responseEntity = messageController.createMessage(message);
//        ResponseEntity<Message> responseEntity1 = messageController.createMessage(message1);
//        Assertions.assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
//        Assertions.assertEquals(responseEntity1.getStatusCode(),HttpStatus.BAD_REQUEST);
//    }
//
//    @Test
//    void test_getRequest(){
//        ResponseEntity<List<Message>> messagesList = messageController.getMessages();
//        Assertions.assertEquals(messagesList.getStatusCode(),HttpStatus.FOUND);
//    }
}
