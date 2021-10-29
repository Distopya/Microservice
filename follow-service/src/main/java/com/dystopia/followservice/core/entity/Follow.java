package com.dystopia.followservice.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "follows")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
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