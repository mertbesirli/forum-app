package com.forum.app.like.service;

import com.forum.app.like.client.PostClient;
import com.forum.app.like.client.UserClient;
import com.forum.app.like.dto.LikeCreateDto;
import com.forum.app.like.dto.PostDto;
import com.forum.app.like.dto.UserDto;
import com.forum.app.like.entity.Like;
import com.forum.app.like.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private UserClient userClient;
    private PostClient postClient;

    public LikeService(LikeRepository likeRepository, UserClient userClient, PostClient postClient) {
        this.likeRepository = likeRepository;
        this.userClient = userClient;
        this.postClient = postClient;
    }

    public List<Like> getLikes(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()) {
            return likeRepository.findByUserIdAndPostId(userId, postId);
        }else if(userId.isPresent()) {
            return likeRepository.findByUserId(userId);
        }else if(postId.isPresent()) {
            return likeRepository.findByUserId(postId);
        }
        return likeRepository.findAll();
    }

    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateDto likeCreateDto) {
        UserDto userDto = userClient.getById(likeCreateDto.getUserId());
        PostDto postDto = postClient.getById(likeCreateDto.getPostId());
        if(userDto == null || postDto == null) {
            throw new RuntimeException("Invalid user or post");
        }
        // change it later
        Like like = new Like();
        like.setUserId(likeCreateDto.getUserId());
        like.setPostId(likeCreateDto.getPostId());
        return likeRepository.save(like);
    }

    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
