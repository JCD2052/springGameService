package org.jcd2052.api.dtoconverters;

import org.jcd2052.api.dto.output.GenreDto;
import org.jcd2052.api.entities.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreDtoConverter implements DtoEntityConverter<Genre, GenreDto> {
    @Override
    public GenreDto convertToDto(Genre entity) {
        return GenreDto.builder()
                .id(entity.getId())
                .genreName(entity.getGenreName())
                .build();
    }
}
