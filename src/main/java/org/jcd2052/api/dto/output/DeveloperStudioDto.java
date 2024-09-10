package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(
        title = "Developer studio record response",
        description = "Represents Developer studio information")
public class DeveloperStudioDto {
    private int id;
    private String developerStudioName;
}
