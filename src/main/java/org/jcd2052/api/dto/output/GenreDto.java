package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@Schema(
        title = "Game genre record response",
        description = "Represents Game genre information")
public class GenreDto {
    private int id;
    private String genreName;
}
