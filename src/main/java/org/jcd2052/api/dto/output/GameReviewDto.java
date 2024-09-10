package org.jcd2052.api.dto.output;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(
        title = "Game review record response",
        description = "Represents Game review information")
public class GameReviewDto {
    private int id;
    private UserDto user;
    private GameDto game;
    private double score;
    private String comment;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timeCreated;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private final LocalDateTime timeUpdated;
}
