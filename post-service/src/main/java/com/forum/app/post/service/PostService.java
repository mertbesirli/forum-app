package com.forum.app.post.service;

import com.forum.app.post.client.UserServiceClient;
import com.forum.app.post.dto.request.PostCreateDto;
import com.forum.app.post.dto.request.PostUpdateDto;
import com.forum.app.post.dto.response.PostResponseDto;
import com.forum.app.post.dto.response.UserResponseDto;
import com.forum.app.post.entity.Post;
import com.forum.app.post.mapper.PostMapper;
import com.forum.app.post.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    private UserServiceClient userServiceClient;

    private PostMapper postMapper;

    public PostService(PostRepository postRepository, UserServiceClient userServiceClient, PostMapper postMapper) {
        this.postRepository = postRepository;
        this.userServiceClient = userServiceClient;
        this.postMapper = postMapper;
    }

    public List<PostResponseDto> getPosts(Optional<Long> userId) {
        List<Post> posts;
        if (userId.isPresent()) {
            posts = postRepository.findByUserId(userId);
        } else {
            posts = postRepository.findAll();
        }

        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        for (Post post : posts) {
            UserResponseDto userResponseDto = userServiceClient.getUserById(post.getUserId());
            PostResponseDto postResponseDto = postMapper.toPostResponseDto(post, userResponseDto);
            postResponseDtos.add(postResponseDto);
        }
        return postResponseDtos;
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(PostCreateDto postCreateDto) {
        UserResponseDto user = userServiceClient.getUserById(postCreateDto.getUserId());
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
