package org.jcd2052.api.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.GenericDto;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@Schema(
        title = "Game review record response",
        description = "Represents Game review information")
public class GameReviewDto extends GenericDto {
    private long id;
    private UserDto user;
    private GameDto game;
    private double score;
    private String comment;
    @JsonFormat(pattern = ApiConstants.APPLICATION_DATE_FORMAT)
    private LocalDateTime timeCreated;
    @JsonFormat(pattern = ApiConstants.APPLICATION_DATE_FORMAT)
    private final LocalDateTime timeUpdated;
}
