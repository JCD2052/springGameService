package org.jcd2052.service.games;

import org.jcd2052.models.Game;
import org.jcd2052.repositories.games.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService extends BaseService<Game> {
    protected GameService(GameRepository repository) {
        super(repository);
    }

    public Game getGameByPlatformAndName(String platformName, String gameName) {
        return ((GameRepository) repository)
                .findGameByPlatformPlatformNameAndGameInfoGameName(platformName, gameName)
                .orElseThrow(() ->
                        new RuntimeException(String.format("Couldn't find game %s on platform %s ",
                                gameName, platformName)));
    }

//    public List<Game> getAllGamesOrderedByAverageRating() {
//        return ((GameRepository) repository).findGameByRating();
//    }
}
