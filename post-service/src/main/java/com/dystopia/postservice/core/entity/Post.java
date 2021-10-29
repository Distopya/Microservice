package com.dystopia.postservice.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "posts")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Post {
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "posts_hashtags",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "hashtag_id")})
    @JsonIgnore
    @ToString.Exclude
    List<Hashtag> hashtags;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Size(min = 5, max = 200)
    @Column(name = "title", nullable = false, length = 200)
    private String title;

    @Size(min = 5, max = 255)
    @Column(name = "description", nullable = false)
    private String description;

    @Lob
    @Type(type = "text")
    @Column(name = "content", nullable = false)
    private String content;

    @NotBlank
    @Column(name = "user", nullable = false)
    private String user;
}
