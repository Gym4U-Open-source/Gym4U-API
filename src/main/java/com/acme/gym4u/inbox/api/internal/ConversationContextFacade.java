package com.acme.gym4u.inbox.api.internal;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;

import java.util.Optional;

public interface ConversationContextFacade {
    Conversation findConversationByCoachIdAndClientId(Long coachId, Long clientId);
    Conversation createConversationByCoachIdAndClientId(Long coachId, Long clientId);
}
