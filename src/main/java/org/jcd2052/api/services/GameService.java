package org.jcd2052.api.services;

import org.jcd2052.api.entities.Game;
import org.jcd2052.api.repositories.GameRepository;
import org.jcd2052.api.exceptionhandler.exceptions.GameNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService extends BaseService<Game> {
    protected GameService(GameRepository repository) {
        super(repository);
    }

    @Override
    public List<Game> fetchEntities(Game probe) {
        return super.fetchEntities(Example.of(
                probe,
                ExampleMatcher.matchingAny().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)));
    }

    public Game getGameByIdOrThrowError(long gameId) {
        return repository.findById(gameId).orElseThrow(() -> new GameNotFoundException(gameId));
    }

    public Optional<Game> isGameExisted(String gameName, long platformId) {
        return ((GameRepository) repository).findGameByGameInfoGameNameAndPlatformId(gameName, platformId);
    }
}
