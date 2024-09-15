package com.forum.app.comment.controller;

import com.forum.app.comment.dto.CommentCreateDto;
import com.forum.app.comment.dto.CommentUpdateDto;
import com.forum.app.comment.entity.Comment;
import com.forum.app.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @GetMapping// /comments?userId={userId}&postId={userId}
    public List<Comment> getComments(@RequestParam(required = false) Optional<Long>  userId, @RequestParam(required = false) Optional<Long> postId) {
        return commentService.getComments(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getComments(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CommentCreateDto commentCreateDto) {
        return commentService.createComment(commentCreateDto);
    }

    @PutMapping("/{commentId}")
    public Comment updateComment(@PathVariable Long commentId, @RequestBody CommentUpdateDto commentUpdateDto) {
        return commentService.updateComment(commentId, commentUpdateDto);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
    }

}
