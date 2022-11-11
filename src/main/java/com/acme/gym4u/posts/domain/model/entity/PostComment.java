package com.acme.gym4u.posts.domain.model.entity;

import com.acme.gym4u.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_comment")
public class PostComment extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String review;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "post_id")
    private Post post;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (!(o instanceof PostComment))
            return false;

        return
                id != null &&
                        id.equals(((PostComment) o).getId());
    }
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}