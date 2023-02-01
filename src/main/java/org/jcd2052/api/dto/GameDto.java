package org.jcd2052.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.api.models.GameRating;
import org.jcd2052.api.models.Platform;

import java.util.Set;

@Data
@NoArgsConstructor
@ToString
public class GameDto {
    private GameInfoDto gameInfo;
    private Platform platform;
    private double averageRating;
    private Set<GameRating> gameRatings;
}
