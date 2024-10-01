package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.dto.GenericDto;
import org.jcd2052.api.entities.enums.PostType;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Schema(
        title = "Post record response",
        description = "Represents Post platform information")
public class PostDto extends GenericDto {
    private Long id;
    private UserDto postUser;
    private PostType postType;
    private String postTitle;
    private String postContent;
    private LocalDateTime timeCreated;
    private LocalDateTime timeUpdate;
}
