package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(
        name = "Auth Input",
        description = "Parameters required to receive auth token",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class AuthDtoInput {
    private String username;
    private String password;
}
