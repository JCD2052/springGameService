package org.jcd2052.api.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.GenericDto;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Schema(
        title = "User record response",
        description = "Represents User information")
public class UserDto extends GenericDto {
    private int id;
    private String username;
    private String userRole;
    @JsonFormat(pattern = ApiConstants.APPLICATION_DATE_FORMAT)
    private LocalDateTime timeCreated;
    private String email;
}
