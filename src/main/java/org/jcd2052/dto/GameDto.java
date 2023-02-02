package org.jcd2052.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class GameDto {
    private GameInfoDto gameInfo;
    private PlatformDto platform;
    private double averageRating;
    @JsonManagedReference
    private Set<GameRatingDto> gameRatings = new HashSet<>();
}
