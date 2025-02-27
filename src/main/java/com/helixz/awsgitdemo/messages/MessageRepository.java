package com.helixz.awsgitdemo.messages;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Chamith Kodikara
 */
@Repository
public interface MessageRepository  extends JpaRepository<Message,Long> {
    @Query("select msg from Message msg where msg.message like :searchTerm")
    Page<Message> findAll(@Param("searchTerm")String searchTerm, Pageable pageable);
}
