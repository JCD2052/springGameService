package org.jcd2052.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class GenreDto {
    private int id;
    private String genreName;
}
