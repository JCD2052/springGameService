package org.jcd2052.api.utils;

import org.jcd2052.api.entities.Game;
import org.jcd2052.api.repsonses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

public class Utils {
    private Utils() {
    }

    public static Set<Game> findNewAndExisted(Set<Game> savedGames, Set<Game> newGames) {
        Set<Game> result = new HashSet<>();
        for (Game newGame : newGames) {
            if (savedGames.stream().map(Game::getPlatform)
                    .noneMatch(platform -> platform.equals(newGame.getPlatform()))) {
                result.add(newGame);
            }
            for (Game savedGame : savedGames) {
                if (newGame.getPlatform().equals(savedGame.getPlatform())) {
                    result.add(savedGame);
                }
            }
        }
        return result;
    }

    public static <T> ResponseEntity<BaseResponse> createResponse(T entity,
                                                                  HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseResponse(entity), httpStatus);
    }
}
