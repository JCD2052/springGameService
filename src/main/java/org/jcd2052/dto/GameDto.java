package org.jcd2052.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    private GameInfoDto gameInfo;
    private PlatformDto platform;
    private double averageRating;
    private Set<GameRatingDto> gameRatings = new HashSet<>();
}
