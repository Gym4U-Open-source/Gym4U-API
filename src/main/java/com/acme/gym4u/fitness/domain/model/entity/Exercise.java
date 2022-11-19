package com.acme.gym4u.fitness.domain.model.entity;

import com.acme.gym4u.fitness.domain.model.enumeration.Aproaches;
import com.acme.gym4u.fitness.domain.model.enumeration.Categories;
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
@Table(name = "exercises")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Exercise extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "category", nullable = false)
    private Categories category;

    @JoinColumn(name = "approach", nullable = false)
    private Aproaches aproach;

    @NotNull
    @NotBlank
    @Size(max=50)
    private String name;

    @NotNull
    @NotBlank
    @Size(max=120)
    private String assetUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id", nullable = false)
    @JsonIgnore
    private Tag tag;
}
