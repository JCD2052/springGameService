package org.jcd2052.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.models.GameGenre;
import org.jcd2052.models.GameInfo;
import org.jcd2052.models.Platform;

import java.util.Set;

@Data
@ToString
@NoArgsConstructor
public class GameInfoDto {
    private String name;
    private String description;
    private int releaseYear;
    private GameGenre gameGenre;
    private DeveloperStudio developerStudio;
    private Set<Platform> platformList;

    public GameInfoDto(GameInfo gameInfo, Set<Platform> platforms) {
        this.name = gameInfo.getGameName();
        this.description = gameInfo.getGameDescription();
        this.releaseYear = gameInfo.getGameReleaseDate();
        this.gameGenre = gameInfo.getGameGenre();
        this.platformList = platforms;
    }
}