package com.dystopia.feedbackservice.core.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Lob
    @Type(type = "text")
    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "post", nullable = false)
    private String post;

    @Column(name = "user", nullable = false)
    private String user;
}
