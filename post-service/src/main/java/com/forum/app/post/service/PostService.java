package com.forum.app.post.service;

import com.forum.app.post.client.UserClient;
import com.forum.app.post.dto.PostCreateDto;
import com.forum.app.post.dto.PostUpdateDto;
import com.forum.app.post.dto.UserDto;
import com.forum.app.post.entity.Post;
import com.forum.app.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    private UserClient userClient;

    public PostService(PostRepository postRepository, UserClient userClient) {
        this.postRepository = postRepository;
        this.userClient = userClient;
    }

    public List<Post> getPosts(Optional<Long> userId) {
        if (userId.isPresent()) {
            return postRepository.findByUserId(userId);
        }
        return postRepository.findAll();
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateDto postCreateDto) {
        UserDto user = userClient.getUserById(postCreateDto.getUserId());
        if(user == null) {
            throw new RuntimeException("User not found");
        }
        // change it later.
        Post post = new Post();
        post.setTitle(postCreateDto.getTitle());
        post.setContent(postCreateDto.getContent());
        post.setUserId(postCreateDto.getUserId());

        return postRepository.save(post);
    }

    public Post updatePost(Long postId, PostUpdateDto postUpdateDto) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post updatedPost = optionalPost.get();
            updatedPost.setTitle(postUpdateDto.getTitle());
            updatedPost.setContent(postUpdateDto.getContent());
            return postRepository.save(updatedPost);
        }
        return null;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }
}
