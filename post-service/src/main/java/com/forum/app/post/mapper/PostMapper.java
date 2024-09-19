package com.forum.app.post.mapper;

import com.forum.app.post.dto.response.PostResponseDto;
import com.forum.app.post.dto.response.UserResponseDto;
import com.forum.app.post.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(source = "userResponseDto.userName", target = "userName")
    PostResponseDto toPostResponseDto(Post post, UserResponseDto userResponseDto);
}
