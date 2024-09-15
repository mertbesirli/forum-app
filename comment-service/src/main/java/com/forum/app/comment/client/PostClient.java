package com.forum.app.comment.client;

import com.forum.app.comment.dto.PostDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "post-service", url = "http://localhost:8082")
public interface PostClient {
    @GetMapping("/posts/{postId}")
    PostDto getPostById(@PathVariable("postId") Long postId);
}
