package com.helixz.awsgitdemo.users;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    @Query("""
    SELECT u FROM User u
    WHERE (:firstName IS NULL OR u.firstName LIKE :firstName)
    AND (:lastName IS NULL OR u.lastName LIKE :lastName)
    AND (:email IS NULL OR u.email LIKE :email)
    """)
    Page<User> searchByFields(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            Pageable pageable
    );

}
