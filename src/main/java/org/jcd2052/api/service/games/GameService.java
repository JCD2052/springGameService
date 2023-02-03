package org.jcd2052.api.service.games;

import org.jcd2052.api.entities.Game;
import org.jcd2052.api.repositories.games.GameRepository;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class GameService extends BaseService<Game> {
    protected GameService(GameRepository repository) {
        super(repository);
    }

    public Game getGameByPlatformAndName(String platformName, String gameName) {
        return ((GameRepository) repository)
                .findGameByPlatformPlatformNameAndGameInfoGameName(platformName, gameName)
                .orElseThrow(() -> new GameNotFoundException(gameName, platformName));
    }

    public Set<Game> findAllGamesByGenreName(String genreName) {
        return ((GameRepository) repository).findAllByGameInfoGameGenreGenreName(genreName);
    }

    public Set<Game> findAllGamesByPlatformName(String platformName) {
        return ((GameRepository) repository).findAllByPlatformPlatformName(platformName);
    }

    public Set<Game> findAllByGameInfoGameDeveloperStudioStudioName(String developerStudioName) {
        return ((GameRepository) repository)
                .findAllByGameInfoGameDeveloperStudioStudioName(developerStudioName);
    }

    public Set<Game> findAllByGameInfoGameReleaseDate(int releaseData) {
        return ((GameRepository) repository).findAllByGameInfoGameReleaseDate(releaseData);
    }

}
