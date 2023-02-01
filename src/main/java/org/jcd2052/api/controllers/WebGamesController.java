//package org.jcd2052.controllers;
//
//import org.jcd2052.dto.GameInfoDto;
//import org.jcd2052.models.GameInfo;
//import org.jcd2052.models.Game;
//import org.jcd2052.models.Platform;
//import org.jcd2052.service.games.DeveloperStudioService;
//import org.jcd2052.service.games.GameGenreService;
//import org.jcd2052.service.games.GameInfoService;
//import org.jcd2052.service.games.GameService;
//import org.jcd2052.service.games.PlatformService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PatchMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Controller
//@RequestMapping("/games")
//public class WebGamesController {
//    private static final String GAME_INFO_DTO_KEY = "gameInfoDto";
//    private static final String GAME_INFO_KEY = "gameInfo";
//    private static final String GAME_KEY = "game";
//    private static final String ID_KEY = "id";
//    private static final String PLATFORMS_KEY = "platforms";
//    private static final String GENRES_KEY = "genres";
//    private static final String DEVELOPERS_KEY = "developers";
//    public static final String PLATFORM_KEY = "platform";
//    private static final String PLATFORM_NAME_KEY = "platformName";
//    private static final String GAME_NAME = "gameName";
//    private final GameService gameService;
//    private final DeveloperStudioService developerStudioService;
//    private final GameGenreService genreService;
//    private final PlatformService platformService;
//    private final GameInfoService gameInfoService;
//
//    @Autowired
//    public WebGamesController(GameService gameService,
//                              DeveloperStudioService developerStudioService,
//                              GameGenreService genreService, PlatformService platformService,
//                              GameInfoService gameInfoService) {
//        this.gameService = gameService;
//        this.developerStudioService = developerStudioService;
//        this.genreService = genreService;
//        this.platformService = platformService;
//        this.gameInfoService = gameInfoService;
//    }
//
//    @GetMapping("/{" + PLATFORM_NAME_KEY + "}/")
//    public String showPlatformGames(@PathVariable(PLATFORM_NAME_KEY)
//                                    String platformName, Model model) {
//        Platform platform = platformService.getPlatformByName(platformName);
//        model.addAttribute(PLATFORM_KEY, platform);
//        return "games/platformGames";
//    }
//
////    @GetMapping
////    public String showMainPage(Model model) {
////        List<Game> games = gameService.getAllGamesOrderedByAverageRating();
////        model.addAttribute("games", games);
////        return "games/showAllGames";
////    }
//
//    @GetMapping("/{platformName}/{gameName}")
//    public String index(@PathVariable(PLATFORM_NAME_KEY) String platformName,
//                        @PathVariable(GAME_NAME) String gameName, Model model) {
//        Game game = gameService.getGameByPlatformAndName(platformName, gameName);
//        model.addAttribute(GAME_KEY, game);
//        return "games/index";
//    }
//
//    @GetMapping("/new")
//    public String newGame(@ModelAttribute(GAME_INFO_DTO_KEY) GameInfoDto gameInfoDto,
//                          Model model) {
//        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
//        model.addAttribute(GENRES_KEY, genreService.getAll());
//        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
//        return "games/addGame";
//    }
//
//    @PostMapping
//    public String addGame(@ModelAttribute(GAME_INFO_DTO_KEY) GameInfoDto gameInfoDto) {
//        GameInfo gameInfo = new GameInfo(gameInfoDto);
//        gameInfoDto.getPlatformList().forEach(platform ->
//                gameService.save(new Game(gameInfo, platform))
//        );
//        return "redirect:/games/";
//    }
//
//    @GetMapping("/{gameName}/edit")
//    public String edit(@PathVariable(GAME_NAME) String gameName, Model model) {
//        model.addAttribute(PLATFORMS_KEY, platformService.getAll());
//        model.addAttribute(GENRES_KEY, genreService.getAll());
//        model.addAttribute(DEVELOPERS_KEY, developerStudioService.getAll());
//
//        GameInfo gameInfo = gameInfoService.findGameInfoByName(gameName);
//        GameInfoDto gameInfoDto = new GameInfoDto(gameInfo, gameInfo.getAllPlatforms());
//        model.addAttribute(GAME_INFO_DTO_KEY, gameInfoDto);
//        return "games/editGame";
//    }
//
//    @PatchMapping("/{gameName}")
//    public String updateGame(@ModelAttribute(GAME_INFO_DTO_KEY) GameInfoDto gameInfoDto,
//                             @PathVariable(GAME_NAME) String gameName) {
//        GameInfo gameInfo = gameInfoService.findGameInfoByName(gameName);
//        gameInfo.setGameGenre(gameInfoDto.getGameGenre());
//        gameInfo.setGameDeveloperStudio(gameInfoDto.getDeveloperStudio());
//        gameInfo.setGameName(gameInfoDto.getName());
//        gameInfo.setGameReleaseDate(gameInfoDto.getReleaseYear());
//        gameInfo.setGameDescription(gameInfoDto.getDescription());
//        Set<Game> games = gameInfoDto.getPlatformList()
//                .stream()
//                .map(platform -> new Game(gameInfo, platform))
//                .collect(Collectors.toSet());
//        gameService.deleteAll(findDeleted(gameInfo.getGames(), games));
//        gameService.saveAll(findNewAndExisted(gameInfo.getGames(), games));
//        return "redirect:/games";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteGame(@PathVariable(ID_KEY) int id) {
//        gameService.deleteById(id);
//        return "redirect:/games";
//    }
//
//    private static Set<Game> findDeleted(Set<Game> savedGames, Set<Game> newGames) {
//        Set<Game> result = new HashSet<>();
//        for (Game savedGame : savedGames) {
//            if (!newGames.contains(savedGame)) {
//                result.add(savedGame);
//            }
//        }
//        return result;
//    }
//
//    private static Set<Game> findNewAndExisted(Set<Game> savedGames, Set<Game> newGames) {
//        Set<Game> result = new HashSet<>();
//        for (Game savedGame : savedGames) {
//            for (Game newGame : newGames) {
//                if (newGame.getPlatform().equals(savedGame.getPlatform())) {
//                    result.add(savedGame);
//                }
//                if (!savedGames.contains(newGame)) {
//                    result.add(newGame);
//                }
//            }
//        }
//        return result;
//    }
//}