package org.jcd2052.api.controllers;

import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.entities.Game;
import org.jcd2052.api.entities.GameInfo;
import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.factories.GameDtoFactory;
import org.jcd2052.api.repositories.games.GameInfoRepository;
import org.jcd2052.api.repsonses.exceptionhandler.exception.DeveloperStudioNotFoundException;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameAlreadyExistedException;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameGenreNotFoundException;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameNotFoundException;
import org.jcd2052.api.repsonses.exceptionhandler.exception.PlatformNotFoundException;

import org.jcd2052.api.service.games.DeveloperStudioService;
import org.jcd2052.api.service.games.GameGenreService;
import org.jcd2052.api.service.games.GameService;
import org.jcd2052.api.service.games.PlatformService;
import org.jcd2052.api.utils.Utils;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.dto.GameDtoInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/games")
@Transactional
public class GamesController {
    private static final String APPLICATION_JSON = "application/json";
    private final GameInfoRepository gameInfoRepository;
    private final GameService gameService;
    private final DeveloperStudioService developerStudioService;
    private final GameGenreService gameGenreService;
    private final PlatformService platformService;

    @Autowired
    public GamesController(
            GameInfoRepository gameInfoRepository,
            GameService gameService,
            DeveloperStudioService developerStudioService,
            GameGenreService gameGenreService,
            PlatformService platformService) {
        this.gameInfoRepository = gameInfoRepository;
        this.gameService = gameService;
        this.developerStudioService = developerStudioService;
        this.gameGenreService = gameGenreService;
        this.platformService = platformService;
    }

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllGames() {
        return Utils.createResponse(GameDtoFactory.createGameDtoList(gameService.getAll()), HttpStatus.OK);
    }

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> addGame(@RequestBody GameDtoInput input) {
        String gameName = input.getName();
        int platformId = input.getPlatformId();
        Optional<Game> gameExisted = gameService.isGameExisted(gameName, platformId);

        if (gameExisted.isEmpty()) {
            GameInfo gameInfo = gameInfoRepository.findGameInfoByGameName(input.getName())
                    .orElse(GameInfo.builder()
                            .gameDescription(input.getGameDescription())
                            .gameName(gameName)
                            .gameGenre(gameGenreService.findGameGenreByIdOrThrowError(input.getGameGenreId()))
                            .build());

            DeveloperStudio developerStudio =
                    developerStudioService.findDeveloperStudioById(input.getGameDeveloperStudioId());
            Platform platform = platformService.findPlatformByIdOrThrowError(platformId);

            Game game = Game.builder()
                    .gameInfo(gameInfo)
                    .platform(platform)
                    .developerStudio(developerStudio)
                    .releaseDate(input.getGameReleaseDate())
                    .build();

            gameInfoRepository.save(gameInfo);
            gameService.save(game);

            return Utils.createResponse(game.toGameDto(), HttpStatus.CREATED);
        }
        throw new GameAlreadyExistedException(gameExisted.get().toGameDto());
    }

    @DeleteMapping(value = "/{gameId}")
    public ResponseEntity<BaseResponse> deleteGame(@PathVariable int gameId) {
        Game gameToDelete = gameService.getGameByIdOrThrowError(gameId);
        GameInfo gameInfo = gameToDelete.getGameInfo();
        Set<Game> games = gameInfo.getGames();
        games.remove(gameToDelete);
        gameInfoRepository.save(gameInfo);
        if (games.isEmpty()) {
            gameInfoRepository.delete(gameInfo);
        }

        return Utils.createResponse(String.format("Game -- %d has been deleted.", gameId), HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleGenreNotFoundException(GameGenreNotFoundException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handeDeveloperStudioNotFoundException(
            DeveloperStudioNotFoundException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleGameNotFoundException(GameNotFoundException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleGameAlreadyExistedException(
            GameAlreadyExistedException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handlePlatformNotFoundException(
            PlatformNotFoundException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleDuplicateException(SQLException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}