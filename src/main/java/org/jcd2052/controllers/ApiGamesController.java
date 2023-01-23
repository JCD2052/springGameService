package org.jcd2052.controllers;

import org.jcd2052.models.Game;
import org.jcd2052.service.games.GameService;
import org.jcd2052.service.games.PlatformService;
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
    private final PlatformService platformService;

    @Autowired
    public ApiGamesController(GameService gameService, PlatformService platformService) {
        this.gameService = gameService;
        this.platformService = platformService;
    }

    @GetMapping
    public Set<Game> getAllGames() {
        return gameService.getAll();
    }

    @GetMapping("/{platformName}/{gameName}")
    public Game getGameByNameAndPlatform(@PathVariable String gameName,
                                         @PathVariable String platformName) {
        Game game = gameService.getGameByPlatformAndName(platformName, gameName);
        double rating = gameService.getGameRating(gameName, platformName);
        game.setAverageRating(rating);
        return game;
    }

    @GetMapping("/platform/{platformName}")
    public Set<Game> getAllGamesByPlatform(@PathVariable String platformName) {
        return platformService.getPlatformByName(platformName).getGames();
    }

    @GetMapping("genre/{genreName}")
    public Set<Game> getAllGamesByGenre(@PathVariable String genreName) {
        return gameService.findAllGamesByGenreName(genreName);
    }

    @GetMapping("developerStudio/{developerStudio}")
    public Set<Game> getAllGamesByDeveloperStudio(@PathVariable String developerStudio) {
        return gameService.findAllByGameInfoGameDeveloperStudioStudioName(developerStudio);
    }
}