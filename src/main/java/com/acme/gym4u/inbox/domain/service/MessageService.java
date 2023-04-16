package com.acme.gym4u.inbox.domain.service;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {
    List<Message> getAll();
    Page<Message> getAll(Pageable pageable);
    Message getById(Long messageId);
    Message create(Message message);
    Message update(Message message);
    ResponseEntity<?> delete(Long messageId);
}
