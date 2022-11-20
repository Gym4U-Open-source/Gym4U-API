package com.acme.gym4u.fitness.domain.model.entity;

import com.acme.gym4u.fitness.domain.model.enumeration.Aproaches;
import com.acme.gym4u.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
@Entity
@Table(name = "aproaches")
public class Aproach extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Aproaches name;
}
