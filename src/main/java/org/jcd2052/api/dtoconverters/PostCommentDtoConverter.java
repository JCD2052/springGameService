package org.jcd2052.api.dtoconverters;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.dto.output.PostCommentDto;
import org.jcd2052.api.entities.PostComment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class PostCommentDtoConverter implements DtoEntityConverter<PostComment, PostCommentDto> {
    private final PostDtoConverter postDtoConverter;

    @Override
    public PostCommentDto convertToDto(PostComment entity) {
        return PostCommentDto.builder()
                .id(entity.getId())
                .postUser(postDtoConverter.getUserDtoConverter().convertToDto(entity.getCommentUser()))
                .post(postDtoConverter.convertToDto(entity.getPost()))
                .content(entity.getComment())
                .timeUpdate(entity.getTimeUpdated())
                .timeCreated(entity.getTimeCreated())
                .build();
    }
}
