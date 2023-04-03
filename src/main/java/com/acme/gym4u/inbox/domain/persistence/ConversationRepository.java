package com.acme.gym4u.inbox.domain.persistence;

import com.acme.gym4u.inbox.domain.model.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByCoachIdOrClientId(Long coachId, Long clientId);
    Conversation findByCoachIdAndClientId(Long coachId, Long clientId);
}
