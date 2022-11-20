package com.acme.gym4u.comunity.domain.model.entity;

import com.acme.gym4u.security.domain.model.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@With
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followers")
public class Follow implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coach_user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User coachUser;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "normal_user_id", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private User normalUser;
}
