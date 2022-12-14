package com.acme.gym4u.security.domain.model.entity;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.profile.domain.model.entity.Profile;
import com.acme.gym4u.security.domain.model.enumns.Focus;
import com.acme.gym4u.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@With
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToOne(

            targetEntity = Profile.class,
            mappedBy = "user"
    )
    private Profile profile;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
