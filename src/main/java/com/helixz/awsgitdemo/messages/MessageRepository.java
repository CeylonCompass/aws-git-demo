package com.helixz.awsgitdemo.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
   @Query(value = "SELECT * FROM message ORDER BY created_date DESC", nativeQuery = true)
   List<Message>findAllByOrderByCreatedDateDesc();

}
