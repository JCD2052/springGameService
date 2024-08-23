package org.jcd2052.api.services;

import org.jcd2052.api.entities.Game;
import org.jcd2052.api.repositories.GameRepository;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameService extends BaseService<Game> {
    protected GameService(GameRepository repository) {
        super(repository);
    }

    public Game getGameByIdOrThrowError(int gameId) {
        return repository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
    }

    public Optional<Game> isGameExisted(String gameName, int platformId) {
        return ((GameRepository) repository)
                .findGameByGameInfoGameNameAndPlatformId(gameName, platformId);
    }
}
