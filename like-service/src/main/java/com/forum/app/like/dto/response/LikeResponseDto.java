package com.forum.app.like.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDto {
    private Long likeId;
    private Long userId;
    private Long postId;
}
