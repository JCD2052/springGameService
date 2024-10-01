package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GenericDto;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(
        title = "Create Post comment Input",
        description = "Parameters required to create\\update post comment record",
        requiredMode = Schema.RequiredMode.AUTO)
public class PostCommentInputDto extends GenericDto {
    private Long postId;
    private String content;
}
