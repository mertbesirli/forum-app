package com.forum.app.like.controller;

import com.forum.app.like.dto.LikeCreateDto;
import com.forum.app.like.entity.Like;
import com.forum.app.like.service.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeService likeService;
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getLikes(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId) {
        return likeService.getLikes(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getLike(@PathVariable Long likeId) {
        return likeService.getLike(likeId);
    }

    @PostMapping
    public Like createLike(@RequestBody LikeCreateDto likeCreateDto) {
        return likeService.createLike(likeCreateDto);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLike(@PathVariable Long likeId) {
        likeService.deleteLike(likeId);
    }
}
