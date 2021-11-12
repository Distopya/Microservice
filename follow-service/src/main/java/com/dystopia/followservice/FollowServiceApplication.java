package com.dystopia.followservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FollowServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FollowServiceApplication.class, args);
    }

}
