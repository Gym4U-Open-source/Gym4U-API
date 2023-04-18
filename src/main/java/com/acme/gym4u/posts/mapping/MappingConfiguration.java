package com.acme.gym4u.posts.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("PostMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PostMapper postMapper() { return new PostMapper();}

    @Bean
    public PostCommentMapper postCommentMapper() {return new PostCommentMapper();}
}
