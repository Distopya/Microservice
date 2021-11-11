package com.dystopia.feedbackservice.config.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String followers;

    private String followings;
}
