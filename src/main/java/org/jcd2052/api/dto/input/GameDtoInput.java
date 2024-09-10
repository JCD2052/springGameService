package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Schema(
        title = "Create Game Input",
        description = "Parameters required to create a game record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class GameDtoInput {
    private String name;
    private String gameDescription;
    private int gameGenreId;
    private int gameDeveloperStudioId;
    private int platformId;
    private LocalDate gameReleaseDate;
}