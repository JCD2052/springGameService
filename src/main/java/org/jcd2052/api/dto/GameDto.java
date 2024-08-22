package org.jcd2052.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

//TODO replace with record.
@Data
@AllArgsConstructor
@Builder
public class GameDto {
    private final long id;
    private final String gameName;
    private final String description;
    private final String gameGenre;
    private final String developerStudio;
    private final String platform;
    private final LocalDate releaseDate;
}
