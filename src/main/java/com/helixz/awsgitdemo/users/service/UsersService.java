package com.helixz.awsgitdemo.users.service;

import com.helixz.awsgitdemo.messages.MessageMapper;
import com.helixz.awsgitdemo.users.UserDetailsEntity;
import com.helixz.awsgitdemo.users.UsersRepository;
import com.helixz.awsgitdemo.users.dto.UserCreateRequest;
import com.helixz.awsgitdemo.users.dto.UserCreateResponse;
import com.helixz.awsgitdemo.users.dto.UserLoginRequest;
import com.helixz.awsgitdemo.users.dto.UserSearchResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    private final MessageMapper messageMapper;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    public UserCreateResponse createResponse(UserCreateRequest userCreateRequest){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder(12);
        String encoded = passwordEncoder.encode(userCreateRequest.getPassword());
        userCreateRequest.setPassword(encoded);
        UserDetailsEntity saved = usersRepository.save(messageMapper.toUsersEntity(userCreateRequest));
        return messageMapper.toUserCreateResponse(saved);
    }

    public UserSearchResponse getUsers(String sortBy, String sortDirection, int page, int pageSize){
        List<UserCreateResponse> list=new ArrayList<>();
        Sort sort=Sort.by(Sort.Order.by(sortBy).with(Sort.Direction.fromString(sortDirection)));
        Pageable pageable= PageRequest.of(page,pageSize,sort);
        Page<UserDetailsEntity> entities = usersRepository.findAll(pageable);
        entities.forEach(userDetailsEntity -> list.add(
                messageMapper.toUserCreateResponse(userDetailsEntity)));
        return UserSearchResponse
                .builder()
                .usersList(list)
                .paginationData(
                        UserSearchResponse.PaginationData
                                .builder()
                                .totalElements(entities.getNumberOfElements())
                                .totalPages(entities.getTotalPages())
                                .pageSize(entities.getSize())
                                .page(entities.getNumber())
                                .build()
                )
                .build();
    }



    public String createJWTToken(UserLoginRequest userLoginRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userLoginRequest.getUsername(),
                userLoginRequest.getPassword()
        ));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userLoginRequest.getUsername());
        }
        return "Token could not created...";
    }
}
