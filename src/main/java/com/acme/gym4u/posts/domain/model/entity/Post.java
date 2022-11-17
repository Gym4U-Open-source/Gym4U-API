package com.acme.gym4u.posts.domain.model.entity;


import com.acme.gym4u.shared.domain.model.AuditModel;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 120)
    @Column(name= "title")
    private String title;


    @Column(name= "description")
    private String description;


    @Column(name= "url_image")
    private String urlImage;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY

    )
    @JoinColumn(name = "post_id")
    private List<PostComment> comments = new ArrayList<>();

    public void addComment(PostComment comment) {
        comments.add(comment);
    }

    public void removeComment(PostComment comment) {
        comments.remove(comment);
    }
}