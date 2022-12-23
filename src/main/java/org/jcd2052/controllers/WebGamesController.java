package org.jcd2052.controllers;

import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.models.GameInfo;
import org.jcd2052.models.Genre;
import org.jcd2052.models.Platform;
import org.jcd2052.service.DeveloperStudioService;
import org.jcd2052.service.GameService;
import org.jcd2052.service.GenreService;
import org.jcd2052.service.PlatformService;
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

import java.util.Collections;

@Controller
@RequestMapping("/games")
public class WebGamesController {
    private static final String GAME_KEY = "game";
    private static final String ID_KEY = "id";
    private static final String PLATFORM_KEY = "platform";
    private static final String GENRE_KEY = "genre";
    private static final String DEVELOPER_STUDIO_KEY = "developer";
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
        return "show";
    }

    @GetMapping("/{id}")
    public String index(@PathVariable(ID_KEY) int id, Model model) {
        GameInfo gameInfo = gameService.getById(id);
        model.addAttribute(GAME_KEY, gameInfo);
        return "index";
    }

    @GetMapping("/new")
    public String newGame(@ModelAttribute(GAME_KEY) GameInfo game,
                          @ModelAttribute(PLATFORM_KEY) Platform platform,
                          @ModelAttribute(GENRE_KEY) Genre genre,
                          @ModelAttribute(DEVELOPER_STUDIO_KEY) DeveloperStudio developerStudio,
                          Model model) {
        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
        model.addAttribute(GENRES_KEY, genreService.getAll());
        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
        return "create";
    }

    @PostMapping
    public String addGame(@ModelAttribute(GAME_KEY) GameInfo game,
                          @ModelAttribute(PLATFORM_KEY) Platform platform,
                          @ModelAttribute(GENRE_KEY) Genre genre,
                          @ModelAttribute(DEVELOPER_STUDIO_KEY) DeveloperStudio developerStudio) {
        Platform selectedPlatform = platformService.getById(platform.getPlatformId());
        Genre selectedGenre = genreService.getById(genre.getGenreId());
        DeveloperStudio selectedDeveloperStudio = developerStudioService
                .getById(developerStudio.getStudioId());

        game.setDeveloperStudio(selectedDeveloperStudio);
        game.setGenre(selectedGenre);
        game.setPlatforms(Collections.singleton(selectedPlatform));

        gameService.save(game);
        return "redirect:/games";
    }

    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute(PLATFORM_KEY) Platform platform,
                       @ModelAttribute(GENRE_KEY) Genre genre,
                       @ModelAttribute(DEVELOPER_STUDIO_KEY) DeveloperStudio developerStudio,
                       @PathVariable(ID_KEY) int id, Model model) {
        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
        model.addAttribute(GENRES_KEY, genreService.getAll());
        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
        model.addAttribute(GAME_KEY, gameService.getById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String updateGame(@ModelAttribute(GAME_KEY) GameInfo game,
                             @ModelAttribute(PLATFORM_KEY) Platform platform,
                             @ModelAttribute(GENRE_KEY) Genre genre,
                             @ModelAttribute(DEVELOPER_STUDIO_KEY) DeveloperStudio developerStudio,
                             @PathVariable(ID_KEY) int id) {
        game.setGenre(genreService.getById(genre.getGenreId()));
        game.setDeveloperStudio(developerStudioService.getById(developerStudio.getStudioId()));
        game.setPlatforms(Collections
                .singleton(platformService.getById(platform.getPlatformId())));

        gameService.updateById(id, game);
        return "index";
    }

    @DeleteMapping("/{id}")
    public String deleteGame(@PathVariable(ID_KEY) int id) {
        gameService.deleteById(id);
        return "redirect:/games";
    }
}