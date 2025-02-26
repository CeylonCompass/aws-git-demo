package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.messages.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User createUser(User user) {
        return repository.save(user);
    }

    public Page<User> searchUsers(String searchTerm, Pageable pageable) {
        Page<User> users;
        if (StringUtils.isBlank(searchTerm)) {
            users = repository.findAll(pageable);
        } else {
            searchTerm = new StringBuilder().append("%").append(searchTerm).append("%").toString();
            users = repository.findAll(searchTerm, pageable);
        }
        return users;
    }
}
