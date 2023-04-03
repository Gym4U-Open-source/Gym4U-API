package com.acme.gym4u.inbox.service;

import com.acme.gym4u.inbox.api.internal.ConversationContextFacade;
import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import com.acme.gym4u.inbox.domain.service.ConversationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConversationFacadeImpl implements ConversationContextFacade {
    private final ConversationService conversationService;

    public ConversationFacadeImpl(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @Override
    public Conversation findConversationByCoachIdAndClientId(Long coachId, Long clientId) {
        return conversationService.getConversationByCoachIdAndClientId(coachId, clientId);
    }

    @Override
    public Conversation createConversationByCoachIdAndClientId(Long coachId, Long clientId) {
        return conversationService.createConversationByCoachIdAndClientId(coachId, clientId);
    }
}
