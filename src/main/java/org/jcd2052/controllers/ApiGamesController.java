package org.jcd2052.controllers;

import org.jcd2052.models.Game;
import org.jcd2052.models.GameInfo;
import org.jcd2052.service.games.GameInfoService;
import org.jcd2052.service.games.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;


@RestController
@RequestMapping("/api/games")
public class ApiGamesController {
    private final GameService gameService;
    private final GameInfoService gameInfoService;

    @Autowired
    public ApiGamesController(GameService gameService, GameInfoService gameInfoService) {
        this.gameService = gameService;
        this.gameInfoService = gameInfoService;
    }

    @GetMapping
    public Set<Game> getAllGames() {
        return gameService.getAll();
    }

    @GetMapping("/{gameName}")
    public GameInfo getGameByName(@PathVariable String gameName) {
        return gameInfoService.findGameInfoByName(gameName);
    }

    @GetMapping("/{platformName}/{gameName}")
    public Game getGameByNameAndPlatform(@PathVariable String gameName,
                                         @PathVariable String platformName) {
        return gameService.getGameByPlatformAndName(platformName, gameName);
    }
}