package com.forum.app.like.client;

import com.forum.app.like.dto.response.PostResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service", url = "http://localhost:8082")
public interface PostClient {
    @GetMapping("/posts/{postId}")
    PostResponseDto getById(@PathVariable("postId") Long postId);
}
