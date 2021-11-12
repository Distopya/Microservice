package com.dystopia.followservice.core.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "follows")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Column(name = "reader", nullable = false)
    private String reader;

    @NotBlank
    @Column(name = "writer", nullable = false)
    private String writer;
}
