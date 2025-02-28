package com.helixz.awsgitdemo.users.service;

import com.helixz.awsgitdemo.users.UserDetailsEntity;
import com.helixz.awsgitdemo.users.UsersRepository;
import com.helixz.awsgitdemo.users.model.UserPrinciple;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetailsEntity userDetailsEntity=usersRepository.findByUsername(username);
        if(userDetailsEntity==null){
            throw new UsernameNotFoundException("User can't find");
        }
        return new UserPrinciple(userDetailsEntity);
    }
}
