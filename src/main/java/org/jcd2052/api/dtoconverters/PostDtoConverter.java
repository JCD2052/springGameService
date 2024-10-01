package org.jcd2052.api.dtoconverters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.dto.output.PostDto;
import org.jcd2052.api.entities.Post;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class PostDtoConverter implements DtoEntityConverter<Post, PostDto> {
    private final UserDtoConverter userDtoConverter;

    @Override
    public PostDto convertToDto(Post entity) {
        return PostDto.builder()
                .id(entity.getId())
                .postTitle(entity.getTitle())
                .postType(entity.getPostType())
                .postUser(userDtoConverter.convertToDto(entity.getPostUser()))
                .postContent(entity.getContent())
                .timeCreated(entity.getTimeCreated())
                .timeUpdate(entity.getTimeUpdated())
                .build();
    }
}
