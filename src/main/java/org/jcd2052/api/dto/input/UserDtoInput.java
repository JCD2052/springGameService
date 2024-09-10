package org.jcd2052.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcd2052.api.entities.UserRole;

@Data
@NoArgsConstructor
@Schema(
        title = "User Input",
        description = "Parameters required to create\\update user record",
        requiredMode = Schema.RequiredMode.REQUIRED)
public class UserDtoInput {
    private String username;
    private String password;
    private String email;
    private UserRole roleName;
}
