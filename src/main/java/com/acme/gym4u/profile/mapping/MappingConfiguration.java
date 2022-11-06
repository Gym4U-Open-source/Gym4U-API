package com.acme.gym4u.profile.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("ProfileMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PersonMapper personMapper() { return new PersonMapper(); }
}
