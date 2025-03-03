package com.helixz.awsgitdemo.users;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import com.helixz.awsgitdemo.users.dto.UserSearchResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private  final UserService userService ;

    @Operation(summary = "Create new user")
    @ApiResponse(responseCode = "201", description = "Successfully created new user")
    @ApiResponse(responseCode = "400", description = "if request body is invalid")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCreateRequest> createNewMessage(@RequestBody @Validated UserCreateRequest request) {
        User user = userService.createUser(userMapper.toUserEntity(request));
        return new ResponseEntity<>(userMapper.toUserResponse(user), HttpStatus.CREATED);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSearchResponse> searchMessages(
            @Parameter(description = "Search term to filter users")
            @RequestParam(name = "firstName", required = false)
            String firstName,
            @RequestParam(name = "lastName", required = false)
            String lastName,
            @RequestParam(name = "email", required = false)
            String email,

            @ParameterObject @SortDefault.SortDefaults({
                    @SortDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC)
            })
            Pageable pageable) {
        Page<User> users = userService.searchUser(firstName,lastName,email, pageable);
        List<UserCreateResponse> userSearchResponse = userMapper.toUserCreateResponse(users.getContent());
        UserSearchResponse response = UserSearchResponse.builder()
                .users(userSearchResponse)
                .paginationData(UserSearchResponse.PaginationData.builder()
                        .totalElements((int) users.getTotalElements())
                        .totalPages(users.getTotalPages())
                        .pageSize(pageable.getPageSize())
                        .page(pageable.getPageNumber())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
