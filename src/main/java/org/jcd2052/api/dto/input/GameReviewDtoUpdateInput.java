package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Schema(
        title = "Update Game Review Input",
        description = "Parameters required to update game review record",
        requiredMode = Schema.RequiredMode.AUTO)
public class GameReviewDtoUpdateInput {
    private Double score;
    private String comment;
}
