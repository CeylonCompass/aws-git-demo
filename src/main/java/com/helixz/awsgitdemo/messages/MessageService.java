package com.helixz.awsgitdemo.messages;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    public ResponseEntity<MessageDto> createMessage(MessageDto message) {
        if (message.content() == null || message.content().isBlank()) {
            throw new EmptyOrNullBodyException("content must not be empty/nul");
        } else {
            return ResponseEntity.ok().body(
                    messageMapper.messageToMessageDto(
                            messageRepository.save(
                                    messageMapper.messageDtoToMessage(message)
                            )
                    )
            );
        }
    }

    public ResponseEntity<List<MessageDto>> getAllMessages() {
        List<Message> messages = messageRepository.findAllByOrderByCreatedDateDesc();

        List<MessageDto> messageDtos = messages.stream()
                .map(messageMapper::messageToMessageDto)
                .collect(Collectors.toList());

        return new ResponseEntity<>(messageDtos, HttpStatus.OK);
    }
}
