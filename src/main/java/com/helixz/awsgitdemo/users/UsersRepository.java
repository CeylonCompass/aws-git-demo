package com.helixz.awsgitdemo.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserDetailsEntity,Long> {

    UserDetailsEntity findByUsername(String username);
    Page<UserDetailsEntity> findAll(Pageable pageable);
}
