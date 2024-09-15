package com.forum.app.comment.client;

import com.forum.app.comment.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service", url = "http://localhost:8081")
public interface UserClient {
    @GetMapping("/users/{userId}")
    UserDto getUserById(@PathVariable("userId") Long userId);
}
