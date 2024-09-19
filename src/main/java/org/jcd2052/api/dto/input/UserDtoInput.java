package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GenericDto;
import org.jcd2052.api.entities.UserRole;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Schema(
        title = "User Input",
        description = "Parameters required to create\\update user record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class UserDtoInput extends GenericDto {
    private String username;
    private String password;
    private String email;
    private UserRole roleName;
}
