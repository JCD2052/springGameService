package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GenericDto;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(
        name = "Auth Input",
        description = "Parameters required to receive auth token",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class AuthDtoInput extends GenericDto {
    private String username;
    private String password;
}
