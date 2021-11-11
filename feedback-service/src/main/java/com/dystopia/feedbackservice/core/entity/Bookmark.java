package com.dystopia.feedbackservice.core.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bookmarks")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "post", nullable = false)
    private String post;

    @Column(name = "user", nullable = false)
    private String user;
}
