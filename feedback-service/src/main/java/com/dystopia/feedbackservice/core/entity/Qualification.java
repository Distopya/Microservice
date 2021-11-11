package com.dystopia.feedbackservice.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;

@Entity
@Table(name = "qualifications")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Qualification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @PositiveOrZero
    @Column(name = "qualification", nullable = false)
    private Integer qualification;

    @Column(name = "post", nullable = false)
    private String post;

    @Column(name = "user", nullable = false)
    private String user;
}
