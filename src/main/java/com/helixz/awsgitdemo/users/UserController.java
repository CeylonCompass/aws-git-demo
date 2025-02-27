package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import com.helixz.awsgitdemo.users.dto.UserSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
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

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @Operation(summary = "Create new User")
    @ApiResponse(responseCode = "201", description = "Successfully created new user")
    @ApiResponse(responseCode = "400", description = "if request body is invalid")
    @PostMapping("/create-user")
    public ResponseEntity<UserCreateResponse> createUser(@RequestBody @Validated UserCreateRequest request) {
        User user = userService.createUser(userMapper.toUserEntity(request));
        return new ResponseEntity<>(userMapper.toUserResponse(user), HttpStatus.CREATED);

    }

    @GetMapping(value = "/search-user", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSearchResponse> searchMessages(
            @Parameter(description = "Search term to filter messages")
            @RequestParam(name = "searchTerm", required = false)
            String searchTerm,
            @ParameterObject @SortDefault.SortDefaults({
                    @SortDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC)
            })
            Pageable pageable) {

        Page<User> users = userService.searchMessages(searchTerm, pageable);
        List<UserCreateResponse> responses = userMapper.toUserCreateResponses(users.getContent());
        UserSearchResponse response = UserSearchResponse.builder()
                .usersList(responses)
                .paginationData(UserSearchResponse.PaginationData.builder()
                        .totalElements((int) users.getTotalElements())
                        .totalPages(users.getTotalPages())
                        .pageSize(pageable.getPageSize())
                        .build())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
