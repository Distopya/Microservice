package com.dystopia.userservice.config.client;

import com.dystopia.userservice.config.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "post-service", url = "http://localhost:8093")
@RequestMapping("/posts")
public interface PostFeignClient {
    @PostMapping()
    Post insertPost(@RequestBody Post post);
}