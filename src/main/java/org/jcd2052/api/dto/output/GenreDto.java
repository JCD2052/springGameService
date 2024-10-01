package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.dto.GenericDto;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@Builder
@Schema(
        title = "Game genre record response",
        description = "Represents Game genre information")
public class GenreDto extends GenericDto {
    private long id;
    private String genreName;
}