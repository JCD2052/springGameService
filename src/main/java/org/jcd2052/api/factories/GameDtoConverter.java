package org.jcd2052.api.factories;

import org.jcd2052.api.dto.GameDto;
import org.jcd2052.api.entities.Game;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class GameDtoConverter implements DtoEntityConverter<Game, GameDto> {
    public GameDto convertToDto(Game entity) {
        return GameDto.builder()
                .id(entity.getId())
                .gameName(entity.getGameInfo().getGameName())
                .description(entity.getGameInfo().getGameDescription())
                .gameGenre(entity.getGameInfo().getGameGenre().getGenreName())
                .platform(entity.getPlatform().getPlatformName())
                .developerStudio(entity.getDeveloperStudio().getStudioName())
                .releaseDate(entity.getReleaseDate())
                .build();
    }

    @Override
    public Comparator<GameDto> createDtoComparator() {
        return Comparator.comparing(GameDto::getId);
    }
}
