package org.jcd2052.api.controllers;

import org.jcd2052.dto.GameInfoDto;
import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.entities.Game;
import org.jcd2052.api.entities.GameGenre;
import org.jcd2052.api.entities.GameInfo;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameNotFoundException;
import org.jcd2052.api.repsonses.exceptionhandler.exception.PlatformNotFoundException;
import org.jcd2052.api.service.games.DeveloperStudioService;
import org.jcd2052.api.service.games.GameGenreService;
import org.jcd2052.api.service.games.GameInfoService;
import org.jcd2052.api.service.games.PlatformService;
import org.jcd2052.api.utils.Utils;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.exceptionhandler.response.GameNotFoundResponse;
import org.jcd2052.api.repsonses.exceptionhandler.response.PlatformNotFoundResponse;
import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.service.games.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/games")
public class ApiGamesController {
    private static final String ENDPOINT_WITH_PLATFORM_AND_GAME_NAME = "/{platformName}/{gameName}";
    private static final String APPLICATION_JSON = "application/json";
    private final GameService gameService;
    private final PlatformService platformService;
    private final GameGenreService genreService;
    private final DeveloperStudioService developerStudioService;
    private final GameInfoService gameInfoService;

    @Autowired
    public ApiGamesController(GameService gameService, PlatformService platformService,
                              GameGenreService genreService,
                              DeveloperStudioService developerStudioService,
                              GameInfoService gameInfoService) {
        this.gameService = gameService;
        this.platformService = platformService;
        this.genreService = genreService;
        this.developerStudioService = developerStudioService;
        this.gameInfoService = gameInfoService;
    }

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> addGame(@RequestBody GameInfoDto gameInfoDto) {
        return Utils.createResponse(createGameFromGameInfoDto(gameInfoDto),
                HttpStatus.CREATED);
    }

    @DeleteMapping(ENDPOINT_WITH_PLATFORM_AND_GAME_NAME)
    public ResponseEntity<BaseResponse> deleteGame(@PathVariable String gameName,
                                                   @PathVariable String platformName) {
        Game gameToDelete = gameService.getGameByPlatformAndName(platformName, gameName);
        GameInfo gameInfo = gameToDelete.getGameInfo();
        Set<Game> games = gameInfo.getGames();
        games.remove(gameToDelete);
        gameInfoService.save(gameInfo);
        if (games.isEmpty()) {
            gameInfoService.deleteEntity(gameInfo);
        }
        String responseMessage = String.format("Game -- %s has been deleted.", gameName);
        return Utils.createResponse(responseMessage, HttpStatus.OK);
    }

    @PatchMapping(value = "/{gameName}", consumes = APPLICATION_JSON,
            produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> updateGame(@PathVariable String gameName,
                                                   @RequestBody GameInfoDto gameInfoDto) {
        GameInfo gameInfo = findAndUpdateGameInfo(gameName, gameInfoDto);
        Set<Game> gamesFromResponse = gameInfoDto.getPlatforms()
                .stream()
                .map(platformName -> new Game(gameInfo, platformService
                        .findPlatformByName(platformName)))
                .collect(Collectors.toSet());
        Set<Game> gamesToSave = Utils.findNewAndExisted(gameInfo.getGames(), gamesFromResponse);
        if (!gamesToSave.isEmpty()) {
            gameInfo.setGames(gamesToSave);
        }
        gameInfoService.save(gameInfo);
        return Utils.createResponse(gameInfo, HttpStatus.OK);
    }

    //TODO instead of endless if-else, need to create a query and perform it.
    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getGames(@RequestParam(defaultValue = "", required = false)
                                                 String gameName,
                                                 @RequestParam(defaultValue = "", required = false)
                                                 String platformName,
                                                 @RequestParam(defaultValue = "", required = false)
                                                 String developerStudio,
                                                 @RequestParam(defaultValue = "", required = false)
                                                 String gameGenre) {
        Optional<Object> response = Optional.empty();
        boolean isGameNameBlank = gameName.isBlank();
        boolean isPlatformNameBlank = platformName.isBlank();
        if (!isGameNameBlank && !isPlatformNameBlank) {
            response = Optional.of(gameService.getGameByPlatformAndName(platformName, gameName));
        } else if (!isGameNameBlank) {
            response = Optional.of(gameInfoService.findGameInfoByName(gameName));
        } else if (!isPlatformNameBlank) {
            response = Optional.of(platformService.findPlatformByName(platformName).getGames());
        } else if (!developerStudio.isBlank()) {
            response = Optional.of(developerStudioService
                    .findDeveloperStudioByStudioName(developerStudio)
                    .getGameInfos());
        } else if (!gameGenre.isBlank()) {
            response = Optional.of(genreService.findGameGenreByGenreName(gameGenre).getGameInfos());
        }
        return Utils.createResponse(response.orElse(gameService.getAll()), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<GameNotFoundResponse> handleGameNotFoundException(
            GameNotFoundException exception) {
        GameNotFoundResponse response = new GameNotFoundResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<PlatformNotFoundResponse> handlePlatformNotFoundException(
            PlatformNotFoundException exception) {
        PlatformNotFoundResponse response = new PlatformNotFoundResponse(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleDuplicateException(SQLException
                                                                          exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private Set<Game> createGameFromGameInfoDto(GameInfoDto gameInfoDto) {
        GameGenre genre = genreService.findGameGenreByGenreName(
                gameInfoDto.getGameGenre());
        DeveloperStudio developerStudio = developerStudioService
                .findDeveloperStudioByStudioName(gameInfoDto.getGameDeveloperStudio());
        Set<Platform> platforms = gameInfoDto.getPlatforms()
                .stream()
                .map(platformService::findPlatformByName)
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
        GameGenre genre = genreService.findGameGenreByGenreName(gameInfoDto.getGameGenre());
        DeveloperStudio developerStudio = developerStudioService
                .findDeveloperStudioByStudioName(gameInfoDto.getGameDeveloperStudio());
        gameInfo.setGameGenre(genre);
        gameInfo.setGameDeveloperStudio(developerStudio);
        return gameInfo;
    }
}