package org.jcd2052.api.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jcd2052.api.entities.UserRole;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDtoInput {
    private String username;
    private String password;
    private String email;
    private UserRole roleName;

    @JsonSetter
    public void setRoleName(int index) {
        this.roleName = UserRole.getUserRoleByIndex(index);
    }
}
