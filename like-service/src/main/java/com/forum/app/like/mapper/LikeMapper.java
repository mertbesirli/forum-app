package com.forum.app.like.mapper;

import com.forum.app.like.dto.response.LikeResponseDto;
import com.forum.app.like.entity.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(source = "like.likeId", target = "likeId")
    LikeResponseDto toLikeResponseDto(Like like);
}
