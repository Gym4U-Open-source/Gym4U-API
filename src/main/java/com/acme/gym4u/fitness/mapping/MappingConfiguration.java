package com.acme.gym4u.fitness.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("fitnessMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public TagMapper tagMapper() { return new TagMapper();}

    @Bean
    public ExerciseMapper exerciseMapper() { return new ExerciseMapper();}

    @Bean
    public TagForWorkoutMapper tagForWorkoutMapper() { return new TagForWorkoutMapper();}

    @Bean
    public WorkoutMapper workoutMapper() { return new WorkoutMapper();}

    @Bean
    public WorkoutExerciseMapper workoutExerciseMapper() {return new WorkoutExerciseMapper();}

    @Bean
    public RoutineMapper routineMapper() { return new RoutineMapper(); }

    @Bean
    public UserRoutineMapper userRoutineMapper() {return new UserRoutineMapper();}
}
