package com.dystopia.feedbackservice.config.client;

import com.dystopia.feedbackservice.config.model.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "post-service", url = "http://localhost:8093")
@FeignClient(name = "post-service")
@RequestMapping("/posts")
public interface PostFeignClient {
    @GetMapping("/{postId}")
    Post findPostById(@PathVariable("postId") String postId);
}
