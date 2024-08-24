package org.jcd2052.api.services;

import org.jcd2052.api.entities.Game;
import org.jcd2052.api.repositories.GameRepository;
import org.jcd2052.api.exceptions.GameNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService extends BaseService<Game> {
    protected GameService(GameRepository repository) {
        super(repository);
    }

    public List<Game> fetchGame(Game probe) {
        return repository.findAll(Example.of(probe));
    }

    public Game getGameByIdOrThrowError(int gameId) {
        return repository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
    }

    public Optional<Game> isGameExisted(String gameName, int platformId) {
        return ((GameRepository) repository).findGameByGameInfoGameNameAndPlatformId(gameName, platformId);
    }
}
