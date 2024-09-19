package com.forum.app.post.controller;

import com.forum.app.post.dto.request.PostCreateDto;
import com.forum.app.post.dto.request.PostUpdateDto;
import com.forum.app.post.dto.response.PostResponseDto;
import com.forum.app.post.entity.Post;
import com.forum.app.post.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<PostResponseDto> getPosts(@RequestParam(required = false) Optional<Long> userId) { // /posts?userId={userId}
        return postService.getPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto);
    }

    @PutMapping("/{postId}")
    public Post updatePost(@PathVariable Long postId, @RequestBody PostUpdateDto postUpdateDto) {
        return postService.updatePost(postId, postUpdateDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
