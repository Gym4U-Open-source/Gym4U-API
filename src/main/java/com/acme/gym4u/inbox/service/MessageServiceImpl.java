package com.acme.gym4u.inbox.service;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import com.acme.gym4u.inbox.domain.model.entity.Message;
import com.acme.gym4u.inbox.domain.persistence.ConversationRepository;
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
    private final ConversationRepository conversationRepository;
    private final Validator validator;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, ConversationRepository conversationRepository, Validator validator) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
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
    public Message create(Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        if (!violations.isEmpty())
            throw  new ResourceValidationException(ENTITY,violations);

        User user = userRepository.findById(message.getUser().getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", message.getUser().getId()));

        Conversation conversation = conversationRepository.findById(message.getConversation().getId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró la conversación con el ID proporcionado", message.getConversation().getId()));

        Message newMessage = new Message().withMessage(message.getMessage()).withConversation(conversation).withUser(user);

        return messageRepository.save(newMessage);
    }

    @Override
    public Message update(Message message) {
        Set<ConstraintViolation<Message>> violations = validator.validate(message);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return messageRepository.findById(message.getId()).map(messages ->
                messageRepository.save(message.withMessage(message.getMessage())
                        .withUser(message.getUser())
                        .withMessage(message.getMessage()))).orElseThrow(() -> new ResourceNotFoundException(ENTITY, message.getId()));
    }

    @Override
    public ResponseEntity<?> delete(Long messageId) {
        return messageRepository.findById(messageId).map(message -> {
            messageRepository.delete(message);
            return ResponseEntity.ok().build();
        }).orElseThrow(()->new ResourceNotFoundException(ENTITY, messageId));
    }
}
