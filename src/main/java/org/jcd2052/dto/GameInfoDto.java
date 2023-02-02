package org.jcd2052.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

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
}