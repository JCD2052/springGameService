package org.jcd2052.api.dtoconverters;

import org.jcd2052.api.dto.output.GameDto;
import org.jcd2052.api.entities.Game;
import org.springframework.stereotype.Component;

@Component
public class GameDtoConverter implements DtoEntityConverter<Game, GameDto> {
    public GameDto convertToDto(Game entity) {
        return GameDto.builder()
                .id(entity.getId())
                .gameName(entity.getGameInfo().getGameName())
                .description(entity.getGameInfo().getGameDescription())
                .gameGenre(entity.getGameInfo().getGenre().getGenreName())
                .platform(entity.getPlatform().getPlatformName())
                .developerStudio(entity.getDeveloperStudio().getStudioName())
                .releaseDate(entity.getReleaseDate())
                .build();
    }
}
