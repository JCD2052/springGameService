package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(
        title = "Game platform record response",
        description = "Represents Game platform information")
public class PlatformDto {
    private int id;
    private String platformName;
}
