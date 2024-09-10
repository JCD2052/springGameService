package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        title = "Game Review Input",
        description = "Parameters required to create a game review record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class GameReviewDtoInput {
    private int gameId;
    private int userReviewerId;
    private String comment;
    private Double score;
}
