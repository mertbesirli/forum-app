package com.forum.app.comment.service;

import com.forum.app.comment.client.PostClient;
import com.forum.app.comment.client.UserClient;
import com.forum.app.comment.dto.CommentCreateDto;
import com.forum.app.comment.dto.CommentUpdateDto;
import com.forum.app.comment.dto.PostDto;
import com.forum.app.comment.dto.UserDto;
import com.forum.app.comment.entity.Comment;
import com.forum.app.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserClient userClient;
    private final PostClient postClient;

    public CommentService(CommentRepository commentRepository, UserClient userClient, PostClient postClient) {
        this.commentRepository = commentRepository;
        this.userClient = userClient;
        this.postClient = postClient;
    }


    public List<Comment> getComments(Optional<Long> userId, Optional<Long> postId) {
        if (userId.isPresent() && postId.isPresent()) {
            return commentRepository.findByUserIdAndPostId(userId, postId);
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId);
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId);
        }
        return commentRepository.findAll();
    }

    public Comment getComments(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createComment(CommentCreateDto commentCreateDto) {
        UserDto userDto = userClient.getUserById(commentCreateDto.getUserId());
        PostDto postDto = postClient.getPostById(commentCreateDto.getPostId());
        if(userDto == null || postDto == null) {
            throw new RuntimeException("Invalid user or post");
        }
        // change it later
        Comment comment = new Comment();
        comment.setContent(commentCreateDto.getContent());
        comment.setUserId(commentCreateDto.getUserId());
        comment.setPostId(commentCreateDto.getPostId());
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, CommentUpdateDto commentUpdateDto) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()) {
            Comment updatedComment = comment.get();
            updatedComment.setContent(commentUpdateDto.getContent());
            return commentRepository.save(updatedComment);
        }
        return null;
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
