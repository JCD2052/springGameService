package org.jcd2052.controllers;

import org.jcd2052.dto.GameDto;
import org.jcd2052.dto.GameInfoDto;
import org.jcd2052.dto.PlatformDto;
import org.jcd2052.exceptionhandler.exception.GameNotFoundException;
import org.jcd2052.exceptionhandler.exception.PlatformNotFoundException;
import org.jcd2052.exceptionhandler.response.GameNotFoundResponse;
import org.jcd2052.exceptionhandler.response.PlatformNotFoundResponse;
import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.models.Game;
import org.jcd2052.models.GameGenre;
import org.jcd2052.models.GameInfo;
import org.jcd2052.models.Platform;
import org.jcd2052.service.games.DeveloperStudioService;
import org.jcd2052.service.games.GameGenreService;
import org.jcd2052.service.games.GameInfoService;
import org.jcd2052.service.games.GameService;
import org.jcd2052.service.games.PlatformService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/games")
public class ApiGamesController {
    private static final String ENDPOINT_WITH_PLATFORM_AND_GAME_NAME = "/{platformName}/{gameName}";
    private final GameService gameService;
    private final ModelMapper modelMapper;
    private final PlatformService platformService;
    private final GameGenreService genreService;
    private final DeveloperStudioService developerStudioService;
    private final GameInfoService gameInfoService;

    @Autowired
    public ApiGamesController(GameService gameService, ModelMapper modelMapper,
                              PlatformService platformService, GameGenreService genreService,
                              DeveloperStudioService developerStudioService,
                              GameInfoService gameInfoService) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
        this.platformService = platformService;
        this.genreService = genreService;
        this.developerStudioService = developerStudioService;
        this.gameInfoService = gameInfoService;
    }

    @PostMapping // should be with response entity
    public Set<Game> addGame(@RequestBody GameInfoDto gameInfoDto) {
        return createGameFromGameInfoDto(gameInfoDto);
    }

    @DeleteMapping(ENDPOINT_WITH_PLATFORM_AND_GAME_NAME)
    @Transactional
    public HttpStatus deleteGame(@PathVariable String gameName,
                                 @PathVariable String platformName) {
        Game gameToDelete = gameService.getGameByPlatformAndName(platformName, gameName);
        GameInfo gameInfo = gameToDelete.getGameInfo();
        Set<Game> games = gameInfo.getGames();
        games.remove(gameToDelete);
        if (games.isEmpty()) {
            gameInfoService.deleteEntity(gameInfo);
        }
        return HttpStatus.OK;
    }

    @PatchMapping("/{gameName}")// should be with response entity
    public GameInfoDto updateGame(@PathVariable String gameName,
                                  @RequestBody GameInfoDto gameInfoDto) {
        GameInfo gameInfo = findAndUpdateGameInfo(gameName, gameInfoDto);

        Set<Game> gamesFromResponse = gameInfoDto.getPlatforms().stream()
                .map(platform -> new Game(gameInfo, platformService
                        .findPlatformByName(platform.getPlatformName())))
                .collect(Collectors.toSet());

        Set<Game> gamesToSave = findNewAndExisted(gameInfo.getGames(), gamesFromResponse);
        if (!gamesToSave.isEmpty()) {
            gameInfo.setGames(gamesToSave);
            gameInfoService.save(gameInfo);
        }
        return new GameInfoDto(gameInfo);
    }

    @GetMapping
    public Set<GameDto> getAllGames() {
        return convertGameCollectionToDto(gameService.getAll());
    }

    @GetMapping(ENDPOINT_WITH_PLATFORM_AND_GAME_NAME)
    public GameDto getGameByNameAndPlatform(@PathVariable String gameName,
                                            @PathVariable String platformName) {
        Game game = gameService.getGameByPlatformAndName(platformName, gameName);
        return convertGameToGameDto(game);
    }

    @GetMapping("/platforms/{platformName}")
    public Set<GameDto> getAllGamesByPlatform(@PathVariable String platformName) {
        return convertGameCollectionToDto(gameService.findAllGamesByPlatformName(platformName));
    }

    @GetMapping("genres/{genreName}")
    public Set<GameDto> getAllGamesByGenre(@PathVariable String genreName) {
        return convertGameCollectionToDto(gameService.findAllGamesByGenreName(genreName));
    }

    @GetMapping("releaseDate/{year}")
    public Set<GameDto> getAllGamesByYear(@PathVariable int year) {
        return convertGameCollectionToDto(gameService.findAllByGameInfoGameReleaseDate(year));
    }

    @GetMapping("developerStudios/{developerStudio}")
    public Set<GameDto> getAllGamesByDeveloperStudio(@PathVariable String developerStudio) {
        return convertGameCollectionToDto(gameService
                .findAllByGameInfoGameDeveloperStudioStudioName(developerStudio));
    }

    @ExceptionHandler
    private ResponseEntity<GameNotFoundResponse> handleGameNotFoundException(
            GameNotFoundException exception) {
        GameNotFoundResponse response = new GameNotFoundResponse(exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PlatformNotFoundResponse> handlePlatformNotFoundException(
            PlatformNotFoundException exception) {
        PlatformNotFoundResponse response = new PlatformNotFoundResponse(exception.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private GameDto convertGameToGameDto(Game game) {
        GameDto dto = modelMapper.map(game, GameDto.class);
        dto.setAverageRating(game.getAverageRating());
        Set<PlatformDto> platforms = game.getOtherPlatforms()
                .stream()
                .map(platform -> new PlatformDto(platform.getPlatformName()))
                .collect(Collectors.toSet());
        dto.getGameInfo().setPlatforms(platforms);
        return dto;
    }

    private Set<GameDto> convertGameCollectionToDto(Collection<Game> games) {
        return games.stream()
                .map(this::convertGameToGameDto)
                .collect(Collectors.toSet());
    }

    private Set<Game> createGameFromGameInfoDto(GameInfoDto gameInfoDto) {
        GameGenre genre = genreService.findGameGenreByGenreName(
                gameInfoDto.getGameGenre().getGenreName());
        DeveloperStudio developerStudio = developerStudioService
                .findDeveloperStudioByStudioName(gameInfoDto.getGameDeveloperStudio()
                        .getStudioName());
        Set<Platform> platforms = gameInfoDto.getPlatforms()
                .stream()
                .map(platformDto -> platformService
                        .findPlatformByName(platformDto.getPlatformName()))
                .collect(Collectors.toSet());

        GameInfo gameInfo = new GameInfo(gameInfoDto.getName(),
                gameInfoDto.getGameDescription(), gameInfoDto.getGameReleaseDate(),
                genre, developerStudio);
        Set<Game> gamesToSave = platforms.stream()
                .map(platform -> new Game(gameInfo, platform))
                .collect(Collectors.toSet());

        gameInfo.setGames(gamesToSave);
        gameInfoService.save(gameInfo);
        return gamesToSave;
    }

    private GameInfo findAndUpdateGameInfo(String gameName, GameInfoDto gameInfoDto) {
        GameInfo gameInfo = gameInfoService.findGameInfoByName(gameName);
        gameInfo.setGameName(gameInfoDto.getName());
        gameInfo.setGameDescription(gameInfoDto.getGameDescription());
        gameInfo.setGameReleaseDate(gameInfoDto.getGameReleaseDate());
        GameGenre genre = genreService.findGameGenreByGenreName(
                gameInfoDto.getGameGenre().getGenreName());
        DeveloperStudio developerStudio = developerStudioService
                .findDeveloperStudioByStudioName(gameInfoDto.getGameDeveloperStudio()
                        .getStudioName());
        gameInfo.setGameGenre(genre);
        gameInfo.setGameDeveloperStudio(developerStudio);
        return gameInfo;
    }

    private static Set<Game> findNewAndExisted(Set<Game> savedGames, Set<Game> newGames) {
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
}