package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Tag(name = "User controller", description = "This is used to handle all the APIs related to Users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "Successfully created new user")
    @ApiResponse(responseCode = "400", description = "if request body is invalid")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreateRequest> createNewMessage(@RequestBody @Validated UserCreateRequest request) {
        User user = userService.createUser(userMapper.toUserEntity(request));
        return new ResponseEntity<>(userMapper.toUserResponse(user), HttpStatus.CREATED);
    }

}
