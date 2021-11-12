package com.dystopia.userservice.config.client;

import com.dystopia.userservice.config.model.Follow;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "follow-service", url = "http://localhost:8092")
@FeignClient(name = "follow-service")
@RequestMapping("/follows")
public interface FollowFeignClient {
    @PostMapping()
    Follow insertFollow(@RequestBody Follow follow);
}
