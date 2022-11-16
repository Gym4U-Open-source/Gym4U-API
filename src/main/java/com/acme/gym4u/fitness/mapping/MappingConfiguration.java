package com.acme.gym4u.fitness.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("learningMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TagMapper tagMapper() { return new TagMapper();}

    @Bean
    public ExerciseMapper exerciseMapper() { return new ExerciseMapper();}

    @Bean
    public TagForWorkoutMapper tagForWorkoutMapper() { return new TagForWorkoutMapper();}
}
