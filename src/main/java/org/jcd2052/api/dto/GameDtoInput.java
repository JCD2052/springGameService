package org.jcd2052.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class GameDtoInput {
    private String name;
    private String gameDescription;
    private int gameGenreId;
    private int gameDeveloperStudioId;
    private int platformId;
    private LocalDate gameReleaseDate;
}