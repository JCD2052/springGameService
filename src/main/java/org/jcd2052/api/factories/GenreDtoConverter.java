package org.jcd2052.api.factories;

import org.jcd2052.api.dto.GenreDto;
import org.jcd2052.api.entities.Genre;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class GenreDtoConverter implements DtoEntityConverter<Genre, GenreDto> {
    @Override
    public GenreDto convertToDto(Genre entity) {
        return GenreDto.builder()
                .id(entity.getId())
                .genreName(entity.getGenreName())
                .build();
    }

    @Override
    public Comparator<GenreDto> createDtoComparator() {
        return Comparator.comparing(GenreDto::getId);
    }
}
