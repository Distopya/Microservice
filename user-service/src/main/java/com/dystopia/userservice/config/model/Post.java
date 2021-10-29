package com.dystopia.userservice.config.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Post {
    private String title;
    private String description;
    private String content;
    private String user;
}
