package com.acme.gym4u.comunity.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("CommunityMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public FollowMapper coachClientMapper() { return new FollowMapper(); }
}
