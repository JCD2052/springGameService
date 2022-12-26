package org.jcd2052.controllers;

import org.jcd2052.models.GameInfo;
import org.jcd2052.service.GameService;
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

    @Autowired
    public ApiGamesController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public Set<GameInfo> getAllGames() {
        return gameService.getAll();
    }

    @GetMapping("/{id}")
    public GameInfo getGameById(@PathVariable int id) {
        return gameService.getById(id);
    }


}
