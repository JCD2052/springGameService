package org.jcd2052.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jcd2052.api.models.GameInfo;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@ToString
@NoArgsConstructor
public class GameInfoDto {
    private String name;
    private String gameDescription;
    private int gameReleaseDate;
    private GameGenreDto gameGenre;
    private DeveloperStudioDto gameDeveloperStudio;
    private Set<PlatformDto> platforms;

    public GameInfoDto(GameInfo gameInfo) {
        this.name = gameInfo.getGameName();
        this.gameDescription = gameInfo.getGameDescription();
        this.gameReleaseDate = gameInfo.getGameReleaseDate();
        this.gameGenre = new GameGenreDto(gameInfo.getGameGenre().getGenreName());
        this.gameDeveloperStudio = new DeveloperStudioDto(gameInfo.getGameDeveloperStudio()
                .getStudioName());
        this.platforms = gameInfo.getAllPlatforms()
                .stream()
                .map(platform -> new PlatformDto(platform.getPlatformName()))
                .collect(Collectors.toSet());
    }
}