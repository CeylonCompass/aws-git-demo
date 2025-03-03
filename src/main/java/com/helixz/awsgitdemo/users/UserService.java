package com.helixz.awsgitdemo.users;

import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helixz.awsgitdemo.exception.impl.ValidationFailedException;

import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserMapper userMapper;
  private  final UserRepository userRepository;

    public User createUser(User user) {
        validateUser(user);
        return userRepository.save(user);
    }


    public Page<User> searchUser(String firstName, String lastName, String email, Pageable pageable) {
        if (StringUtils.isBlank(firstName) &&
            StringUtils.isBlank(lastName)  &&
            StringUtils.isBlank(email)
        ) {
            return userRepository.findAll(pageable);
        }

        String firstNameSearch = StringUtils.isNotBlank(firstName) ? "%" + firstName + "%" : null;
        String lastNameSearch = StringUtils.isNotBlank(lastName) ? "%" + lastName + "%" : null;
        String emailSearch = StringUtils.isNotBlank(email) ? "%" + email + "%" : null;

        return userRepository.searchByFields(firstNameSearch, lastNameSearch, emailSearch, pageable);
    }

    private void validateUser(User user) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (user == null) {
            throw new ValidationFailedException("User object must not be null");
        }
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new ValidationFailedException("First Name must not be empty or null");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new ValidationFailedException("Last Name must not be empty or null");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new ValidationFailedException("Email must not be empty or null");
        }
        if (!Pattern.matches(emailRegex, user.getEmail().trim())) {
            throw new ValidationFailedException("Invalid email format");
        }
    }



}