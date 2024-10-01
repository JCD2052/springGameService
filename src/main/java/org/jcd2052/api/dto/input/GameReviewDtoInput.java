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
        title = "Game Review Input",
        description = "Parameters required to create a game review record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class GameReviewDtoInput extends GenericDto {
    private long gameId;
    private long userReviewerId;
    private String comment;
    private Double score;
}
