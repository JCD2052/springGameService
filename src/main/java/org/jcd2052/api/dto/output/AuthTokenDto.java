package org.jcd2052.api.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.dto.GenericDto;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Schema(
        name = "Authentication response",
        description = "Represents token information")
public class AuthTokenDto extends GenericDto {
    private String token;
    private long expiresIn;
}
