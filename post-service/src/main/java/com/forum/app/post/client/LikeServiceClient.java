package com.forum.app.post.client;

import com.forum.app.post.dto.response.LikeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "like-service", url = "http://localhost:8083")
public interface LikeServiceClient {
    @GetMapping("/likes")
    List<LikeResponseDto> getLikesByPostId(@RequestParam("postId") Long postId);
}
