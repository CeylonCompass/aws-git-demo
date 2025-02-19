package com.helixz.awsgitdemo.messages;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Chamith Kodikara
 */
@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
}
