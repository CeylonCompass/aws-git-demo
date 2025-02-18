package com.helixz.awsgitdemo.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Chamith Kodikara
 */
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByOrderByCreatedDateDesc();
}
