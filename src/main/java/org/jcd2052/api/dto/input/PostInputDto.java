package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GenericDto;
import org.jcd2052.api.entities.enums.PostType;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(
        title = "Create Post Input",
        description = "Parameters required to create\\update post record",
        requiredMode = Schema.RequiredMode.AUTO)
public class PostInputDto extends GenericDto {
    private String title;
    private String content;
    private PostType type;
}
