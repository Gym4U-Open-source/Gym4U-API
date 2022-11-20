package com.acme.gym4u.fitness.domain.model.entity;

import com.acme.gym4u.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "workoutsExercises")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class WorkoutExercise extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "workout_id", nullable = false)
    @JsonIgnore
    private Workout workout;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "exercise_id", nullable = false)
    @JsonIgnore
    private Exercise exercise;

    @Column(name = "repetitions", nullable = false)
    private Long repetitions;

    @Column(name = "timePerRepeat", nullable = false)
    private Long timePerRepeat;
}
