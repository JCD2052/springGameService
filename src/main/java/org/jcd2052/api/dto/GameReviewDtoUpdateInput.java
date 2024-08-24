package org.jcd2052.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GameReviewDtoUpdateInput {
    private Double score;
    private String comment;
}
