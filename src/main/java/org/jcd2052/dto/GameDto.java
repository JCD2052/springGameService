package org.jcd2052.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.models.Platform;

@Data
@NoArgsConstructor
@ToString
public class GameDto {
    private GameInfoDto gameInfo;
    private Platform platform;
    private double averageRating;
}
