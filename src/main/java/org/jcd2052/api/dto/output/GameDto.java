package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.dto.GenericDto;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Schema(
        title = "Game record response",
        description = "Represents Game information")
public class GameDto extends GenericDto {
    private final long id;
    private final String gameName;
    private final String description;
    private final String gameGenre;
    private final String developerStudio;
    private final String platform;
    private final LocalDate releaseDate;
}
