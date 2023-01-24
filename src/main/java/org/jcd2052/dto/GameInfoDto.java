package org.jcd2052.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.models.GameGenre;
import org.jcd2052.models.Platform;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
public class GameInfoDto {
    private String name;
    private String gameDescription;
    private int gameReleaseDate;
    private GameGenre gameGenre;
    private DeveloperStudio gameDeveloperStudio;
    private Set<Platform> otherPlatforms;
}