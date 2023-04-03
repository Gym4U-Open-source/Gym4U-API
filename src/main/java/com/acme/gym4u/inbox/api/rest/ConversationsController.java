package com.acme.gym4u.inbox.api.rest;

import com.acme.gym4u.inbox.domain.service.ConversationService;
import com.acme.gym4u.inbox.mapping.ConversationMapper;
import com.acme.gym4u.inbox.resource.conversation.ConversationResource;
import com.acme.gym4u.inbox.resource.conversation.CreateConversationResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/conversations", produces = "application/json")
@Tag(name = "Conversations", description = "Creat & Get conversations.")
public class ConversationsController {
    private final ConversationService conversationService;
    private final ConversationMapper mapper;


    public ConversationsController(ConversationService conversationService, ConversationMapper mapper) {
        this.conversationService = conversationService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ConversationResource> createConversation(@RequestBody CreateConversationResource resource) {
        return new ResponseEntity<>(mapper.toResource(conversationService.createConversation(mapper.toModel(resource))), HttpStatus.CREATED);
    }

    @GetMapping("/coach/{coachId}/client/{clientId}")
    public ResponseEntity<ConversationResource> getConversationsByCoachIdAndClientId(@PathVariable Long coachId, @PathVariable Long clientId) {
        return new ResponseEntity<>(mapper.toResource(conversationService.getConversationByCoachIdAndClientId(coachId, clientId)), HttpStatus.FOUND);
    }
}
