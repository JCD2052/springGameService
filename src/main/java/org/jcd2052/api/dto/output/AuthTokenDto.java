package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Schema(
        name = "Authentication response",
        description = "Represents token information")
public class AuthTokenDto {
    private String token;
    private long expiresIn;
}
