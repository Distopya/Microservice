package com.dystopia.userservice.core.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NaturalId
    @Size(min = 3, max = 15)
    @Column(name = "username", nullable = false, length = 15, unique = true)
    private String username;

    @NotBlank
    @Size(min = 5, max = 15)
    @Column(name = "password", nullable = false, length = 15)
    private String password;

    @NotBlank
    @Size(max = 25)
    @Column(name = "first_name", nullable = false, length = 25)
    private String firstName;

    @NotBlank
    @Size(max = 25)
    @Column(name = "last_name", nullable = false, length = 25)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotBlank
    @Size(max = 15)
    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @NotBlank
    @Column(name = "followers", nullable = false)
    private String followers;

    @NotBlank
    @Column(name = "followings", nullable = false)
    private String followings;
}
