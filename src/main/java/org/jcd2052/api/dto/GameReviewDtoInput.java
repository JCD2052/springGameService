package org.jcd2052.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameReviewDtoInput {
    private int gameId;
    private int userReviewerId;
    private String comment;
    private Double score;
}
