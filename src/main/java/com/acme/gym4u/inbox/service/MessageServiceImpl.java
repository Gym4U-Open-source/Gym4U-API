package com.acme.gym4u.inbox.service;

import com.acme.gym4u.inbox.domain.model.entity.Message;
import com.acme.gym4u.inbox.domain.persistence.MessageRepository;
import com.acme.gym4u.inbox.domain.service.MessageService;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.domain.persistence.UserRepository;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import com.acme.gym4u.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

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
        return messageRepository.findById(messageId).
                orElseThrow(()->new ResourceNotFoundException(ENTITY,messageId));
    }


    @Override
    public Message create(Long userId, Message message) {

        Set<ConstraintViolation<Message>> violations=validator.validate(message);
        if(!violations.isEmpty())
            throw  new ResourceValidationException(ENTITY,violations);

        User user= userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User",userId));

        message.setUser(user);

        return messageRepository.save(message);
    }

    @Override
    public Message update(Long messageId, Message request) {
        Set<ConstraintViolation<Message>> violations = validator.validate(request);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return messageRepository.findById(messageId).map(message ->
                messageRepository.save(message.withMessage(request.getMessage())
                        .withUser(request.getUser())
                        .withMessage(request.getMessage()))).orElseThrow(() -> new ResourceNotFoundException(ENTITY, messageId));
    }

    @Override
    public ResponseEntity<?> delete(Long messageId) {
        return messageRepository.findById(messageId).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, messageId));
    }
}
