package com.acme.gym4u.profile.domain.model.entity;

import com.acme.gym4u.posts.domain.model.entity.Post;
import com.acme.gym4u.posts.domain.model.entity.PostComment;
import com.acme.gym4u.security.domain.model.entity.User;
import com.acme.gym4u.shared.domain.model.AuditModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name = "profiles")
public class Profile extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String lastName;

    @OneToOne(
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "profile",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
        post.setProfile(this);
    }

    public void removeComment(PostComment comment) {
        posts.remove(comment);
        comment.setPost(null);
    }
}
