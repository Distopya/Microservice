package com.dystopia.feedbackservice.config.client;

import com.dystopia.feedbackservice.config.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "user-service", url = "http://localhost:8091")
@FeignClient(name = "user-service")
@RequestMapping("/users")
public interface UserFeignClient {
    @GetMapping("/{userId}")
    User findUserById(@PathVariable("userId") String userId);

}
