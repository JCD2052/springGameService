package org.jcd2052.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class GameReviewDto {
    private int id;
    private UserDto user;
    private GameDto game;
    private double score;
    private String comment;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timeCreated;
}
