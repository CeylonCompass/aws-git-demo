package com.helixz.awsgitdemo.users;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public User createUser(User userEntity) {
        return userRepository.save(userEntity);
    }

    public Page<User> searchMessages(String searchTerm, Pageable pageable) {
        Page<User> users;
        if (StringUtils.isBlank(searchTerm)) {
            users = userRepository.findAll(pageable);
        } else {
            searchTerm = new StringBuilder().append("%").append(searchTerm).append("%").toString();
            users = userRepository.findAll(searchTerm, pageable);
        }
        return users;
    }
}
