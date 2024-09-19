package com.forum.app.post.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateDto {
    private String title;
    private String content;
    private Long userId; // come to user-service
}
