package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GenericDto;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(
        title = "Create Game Input",
        description = "Parameters required to create a game record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class GameDtoInput extends GenericDto {
    private String name;
    private String gameDescription;
    private long gameGenreId;
    private long gameDeveloperStudioId;
    private long platformId;
    private LocalDate gameReleaseDate;
}