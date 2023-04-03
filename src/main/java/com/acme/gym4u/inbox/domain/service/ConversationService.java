package com.acme.gym4u.inbox.domain.service;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;

import java.util.List;

public interface ConversationService {
    Conversation createConversation(Conversation conversation);
    List<Conversation> getConversationsByUserId(Long userId);
    Conversation getConversationByCoachIdAndClientId(Long coachId, Long clientId);
    Conversation createConversationByCoachIdAndClientId(Long coachId, Long clientId);
}
