package org.jcd2052.api.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GameDto;
import org.jcd2052.api.entities.Game;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameDtoFactory {
    public static List<GameDto> createGameDtoList(Collection<Game> games) {
        return games.stream().map(Game::toGameDto).toList();
    }
}
