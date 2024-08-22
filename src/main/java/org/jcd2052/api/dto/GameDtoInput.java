package org.jcd2052.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@NoArgsConstructor
public class GameDtoInput {
    private String name;
    private String gameDescription;
    private int gameGenreId;
    private int gameDeveloperStudioId;
    private int platformId;
    private LocalDate gameReleaseDate;
}