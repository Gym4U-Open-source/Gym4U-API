package com.acme.gym4u.shared.mapping;

import com.acme.gym4u.profile.mapping.ProfileMapper;
import com.acme.gym4u.security.mapping.RoleMapper;
import com.acme.gym4u.security.mapping.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("enhancedModelMapperConfiguration")
public class MappingConfiguration {

    @Bean
    public EnhancedModelMapper modelMapper() {
        return new EnhancedModelMapper();
    }

    @Bean
public UserMapper userMapper() { return new UserMapper();}

    @Bean
    public RoleMapper roleMapper() { return new RoleMapper();}
}
