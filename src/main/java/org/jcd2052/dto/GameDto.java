package org.jcd2052.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameDto {
    private String name;
    private String description;
    private int releaseYear;
    private int studioId;
    private int platformId;
    private int genreId;
}
