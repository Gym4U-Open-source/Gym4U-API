package com.acme.gym4u.inbox.service;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import com.acme.gym4u.inbox.domain.persistence.ConversationRepository;
import com.acme.gym4u.inbox.domain.service.ConversationService;
import com.acme.gym4u.security.api.internal.UserContextFacade;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.security.domain.model.enumns.Roles;
import com.acme.gym4u.shared.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConversationServiceImpl implements ConversationService {
    private static final String ENTITY = "Conversation";
    private final ConversationRepository conversationRepository;
    private final UserContextFacade userContextFacade;

    public ConversationServiceImpl(ConversationRepository conversationRepository, UserContextFacade userContextFacade) {
        this.conversationRepository = conversationRepository;
        this.userContextFacade = userContextFacade;
    }

    @Override
    public Conversation createConversation(Conversation conversation) {
        User coach = userContextFacade.findByUserId(conversation.getCoach().getId()).orElseThrow(() -> new ResourceNotFoundException("Coach not found", conversation.getCoach().getId()));
        User client = userContextFacade.findByUserId(conversation.getClient().getId()).orElseThrow(() -> new ResourceNotFoundException("Client not found", conversation.getClient().getId()));

        boolean hasCoachRole = coach.getRoles().stream().anyMatch(role -> role.getName().equals(Roles.COACH));

        if (!hasCoachRole) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User haven't coach role");

        Conversation existingConversation = conversationRepository.findByCoachIdAndClientId(conversation.getCoach().getId(), conversation.getClient().getId());

        if (existingConversation != null) throw new ResponseStatusException(HttpStatus.CONFLICT, "Existing conversation");

        Conversation newConversation = new Conversation().withClient(client).withCoach(coach);

        return conversationRepository.save(newConversation);
    }

    @Override
    public List<Conversation> getConversationsByUserId(Long userId) {
        User user = userContextFacade.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundException("Coach not found", userId));
        return conversationRepository.findByCoachIdOrClientId(user.getId(), user.getId());
    }

    @Override
    public Conversation getConversationByCoachIdAndClientId(Long coachId, Long clientId) {
        return conversationRepository.findByCoachIdAndClientId(coachId, clientId);
    }

    @Override
    public Conversation createConversationByCoachIdAndClientId(Long coachId, Long clientId) {
        User coach = userContextFacade.findByUserId(coachId).orElseThrow(() -> new ResourceNotFoundException("Coach not found", coachId));
        User client = userContextFacade.findByUserId(clientId).orElseThrow(() -> new ResourceNotFoundException("Client not found", clientId));

        Conversation newConversation = new Conversation().withClient(client).withCoach(coach);

        return conversationRepository.save(newConversation);
    }
}
