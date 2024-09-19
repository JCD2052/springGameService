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
        title = "Update Game Review Input",
        description = "Parameters required to update game review record",
        requiredMode = Schema.RequiredMode.AUTO)
public class GameReviewDtoUpdateInput extends GenericDto {
    private Double score;
    private String comment;
}
