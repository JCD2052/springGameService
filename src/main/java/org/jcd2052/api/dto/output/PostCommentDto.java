package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.dto.GenericDto;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Schema(
        title = "Post comment record response",
        description = "Represents Post comment information")
public class PostCommentDto extends GenericDto {
    private Long id;
    private UserDto postUser;
    private PostDto post;
    private String content;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdate;
}
