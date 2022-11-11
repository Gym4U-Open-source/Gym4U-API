package com.acme.gym4u.inbox.domain.persistence;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    Message findByMessage(String message);

}
