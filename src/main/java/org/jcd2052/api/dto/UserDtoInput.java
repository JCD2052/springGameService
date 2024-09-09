package org.jcd2052.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcd2052.api.entities.UserRole;

@Data
@NoArgsConstructor
public class UserDtoInput {
    private String username;
    private String password;
    private String email;
    private UserRole roleName;
}
