package org.jcd2052.api.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GameDto;
import org.jcd2052.api.entities.Game;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameDtoFactory {
    public static GameDto createGameDto(Game entity) {
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

    public static List<GameDto> createGameDtoList(Collection<Game> entities) {
        return entities.stream()
                .map(GameDtoFactory::createGameDto)
                .sorted(Comparator.comparing(GameDto::getId))
                .toList();
    }
}
