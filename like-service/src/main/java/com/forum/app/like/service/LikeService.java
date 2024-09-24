package com.forum.app.like.service;

import com.forum.app.like.client.PostClient;
import com.forum.app.like.client.UserClient;
import com.forum.app.like.dto.request.LikeCreateDto;
import com.forum.app.like.dto.response.LikeResponseDto;
import com.forum.app.like.dto.response.PostResponseDto;
import com.forum.app.like.dto.response.UserResponseDto;
import com.forum.app.like.entity.Like;
import com.forum.app.like.mapper.LikeMapper;
import com.forum.app.like.repository.LikeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private UserClient userClient;
    private PostClient postClient;
    private LikeMapper likeMapper;

    public LikeService(LikeRepository likeRepository, UserClient userClient, PostClient postClient, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.userClient = userClient;
        this.postClient = postClient;
        this.likeMapper = likeMapper;
    }

    public List<LikeResponseDto> getLikes(Optional<Long> userId, Optional<Long> postId) {
        List<Like> likeList;
        if(userId.isPresent() && postId.isPresent()) {
            likeList = likeRepository.findByUserIdAndPostId(userId, postId);
        }else if(userId.isPresent()) {
            likeList = likeRepository.findByUserId(userId);
        }else if(postId.isPresent()) {
            likeList = likeRepository.findByPostId(postId);
        }else {
            likeList = likeRepository.findAll();
        }
        List<LikeResponseDto> likeResponseDtos = new ArrayList<>();
        for(Like like : likeList){
            likeResponseDtos.add(likeMapper.toLikeResponseDto(like));
        }
        return likeResponseDtos;
    }

    public Like getLike(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createLike(LikeCreateDto likeCreateDto) {
        UserResponseDto userResponseDto = userClient.getById(likeCreateDto.getUserId());
        PostResponseDto postResponseDto = postClient.getById(likeCreateDto.getPostId());
        if(userResponseDto == null || postResponseDto == null) {
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
