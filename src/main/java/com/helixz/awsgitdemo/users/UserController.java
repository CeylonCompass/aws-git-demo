package com.helixz.awsgitdemo.users;

import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import com.helixz.awsgitdemo.users.dto.UserSearchResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public ResponseEntity<UserCreateRequest> createNewUser(@RequestBody @Validated UserCreateRequest request) {
        User user = userService.createUser(userMapper.toUserEntity(request));
        return new ResponseEntity<>(userMapper.toUserResponse(user), HttpStatus.CREATED);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserSearchResponse> searchUsers(
            @Parameter(description = "Search term to filter users")
            @RequestParam(name = "searchTerm",required = false)
            String searchTerm,

            @ParameterObject @SortDefault.SortDefaults({
                    @SortDefault(sort = {"createdDate"}, direction = Sort.Direction.DESC)
            }) Pageable pageable) {

        Page<User> users = userService.searchUsers(searchTerm, pageable);
        List<UserCreateResponse> responseList = userMapper.toUserCreateResponse(users.getContent());
        UserSearchResponse response = UserSearchResponse.builder()
                .contents(responseList)
                .paginationData(UserSearchResponse.PaginationData.builder()
                        .totalPages(users.getTotalPages())
                        .totalElements((int) users.getTotalElements())
                        .pageSize(pageable.getPageSize())
                        .page(pageable.getPageNumber())
                        .build())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);


    }
}
