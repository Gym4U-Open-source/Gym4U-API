package com.acme.gym4u.inbox.domain.model.entity;

import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")

public class Message extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id",referencedColumnName = "id")
    @JsonIgnore
    private User toUser;

    @ManyToOne()
    @JoinColumn(name = "from_user_id", referencedColumnName = "id")
    @JsonIgnore
    private User fromUser;


    @NotBlank
    @NotNull
    @Size(max = 240)
    @Column(name = "message")
    private String message;

}
