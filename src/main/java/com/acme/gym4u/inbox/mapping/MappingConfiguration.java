package com.acme.gym4u.inbox.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("inboxMappingConfiguration")
public class MappingConfiguration {

    @Bean
    public MessageMapper messageMapper() {
        return new MessageMapper();
    }

    @Bean
    public ConversationMapper conversationMapper() { return new ConversationMapper(); }
}
