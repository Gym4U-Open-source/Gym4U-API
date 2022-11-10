package com.acme.gym4u.inbox.service;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import com.acme.gym4u.inbox.domain.persistence.MessageRepository;
import com.acme.gym4u.inbox.domain.service.MessageService;
import com.acme.gym4u.security.domain.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    private static final String ENTITY ="Message";

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final Validator validator;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, Validator validator) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.validator = validator;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return messageRepository.findAll();
    }

    @Override
    public Page<Message> getAll(Pageable pageable) {
        return messageRepository.findAll(pageable);
    }

    @Override
    public Message getById(Long messageId) {
        return null;
    }

    @Override
    public Message create(Message message) {
        return null;
    }

    @Override
    public Message update(Long messageId, Message request) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long messageId) {
        return null;
    }
}
