package com.helixz.awsgitdemo.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.name like :searchTerm")
    Page<User> findAll(@Param("searchTerm") String searchTerm, Pageable pageable);
}
