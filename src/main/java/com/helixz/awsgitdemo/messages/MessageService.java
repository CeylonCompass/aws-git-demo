package com.helixz.awsgitdemo.messages;

import com.helixz.awsgitdemo.messages.dto.MessageSearchResponse;
import com.helixz.awsgitdemo.users.UserDetailsEntity;
import com.helixz.awsgitdemo.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageMapper messageMapper;
    private final MessageRepository messageRepository;
    private final UsersRepository usersRepository;

    public MessageDTO createMessage(MessageDTO messageDTO) {
        String username=getAuthenticatedUsername();
        log.info("Creating message with user: {}", username);
        UserDetailsEntity userDetailsEntity = usersRepository.findByUsername(username);
        Message message = messageMapper.toMsg(messageDTO);
        message.setUser_id(userDetailsEntity.getId());
        return messageMapper.toDTO(messageRepository.save(message));
    }

    private String getAuthenticatedUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    public MessageSearchResponse getMessagesSorted(String sortBy, String sortDirection, int page, int size) {
        List<MessageDTO> messageDTOList = new ArrayList<>();
        Sort sort = Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(sortDirection)));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Message> all = messageRepository.findAll(pageable);
        all.forEach(message -> messageDTOList.add(messageMapper.toDTO(message)));
        return MessageSearchResponse
                .builder()
                .contents(messageDTOList)
                .paginationData(
                        MessageSearchResponse.PaginationData
                                .builder()
                                .totalElements(all.getNumberOfElements())
                                .totalPages(all.getTotalPages())
                                .pageSize(all.getSize())
                                .page(all.getNumber())
                                .build()
                )
                .build();
    }
}