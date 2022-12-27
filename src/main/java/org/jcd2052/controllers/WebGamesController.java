package org.jcd2052.controllers;

import org.jcd2052.models.GameInfo;
import org.jcd2052.service.games.DeveloperStudioService;
import org.jcd2052.service.games.GameService;
import org.jcd2052.service.games.GenreService;
import org.jcd2052.service.games.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class WebGamesController {
    private static final String GAME_KEY = "game";
    private static final String ID_KEY = "id";
    private static final String PLATFORMS_KEY = "platforms";
    private static final String GENRES_KEY = "genres";
    private static final String DEVELOPERS_KEY = "developers";
    private final GameService gameService;
    private final DeveloperStudioService developerStudioService;
    private final GenreService genreService;
    private final PlatformService platformService;

    @Autowired
    public WebGamesController(GameService gameService, DeveloperStudioService developerStudioService,
                              GenreService genreService, PlatformService platformService) {
        this.gameService = gameService;
        this.developerStudioService = developerStudioService;
        this.genreService = genreService;
        this.platformService = platformService;
    }

    @GetMapping
    public String show(Model model) {
        model.addAttribute("games", gameService.getAll());
        return "games/show";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable(ID_KEY) int id, Model model) {
        GameInfo gameInfo = gameService.getById(id);
//        System.out.println("Game : " + gameInfo.getUsers());
        model.addAttribute(GAME_KEY, gameInfo);
        return "games/index";
    }

    @GetMapping("/new")
    public String newGame(@ModelAttribute(GAME_KEY) GameInfo game, Model model) {
        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
        model.addAttribute(GENRES_KEY, genreService.getAll());
        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
        return "games/create";
    }

    @PostMapping
    public String addGame(@ModelAttribute(GAME_KEY) GameInfo game) {
        gameService.save(game);
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable(ID_KEY) int id, Model model) {
        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
        model.addAttribute(GENRES_KEY, genreService.getAll());
        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
        model.addAttribute(GAME_KEY, gameService.getById(id));
        return "games/edit";
    }

    @PatchMapping("/{id}")
    public String updateGame(@ModelAttribute(GAME_KEY) GameInfo game,
                             @PathVariable(ID_KEY) int id) {
        gameService.updateById(id, game);
        return "games/index";
    }

    @DeleteMapping("/{id}")
    public String deleteGame(@PathVariable(ID_KEY) int id) {
        gameService.deleteById(id);
        return "redirect:/games";
    }
}