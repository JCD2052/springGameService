package org.jcd2052.controllers;

import org.jcd2052.dto.GameDto;
import org.jcd2052.exceptionhandler.exception.GameNotFoundException;
import org.jcd2052.exceptionhandler.exception.PlatformNotFoundException;
import org.jcd2052.exceptionhandler.response.GameNotFoundResponse;
import org.jcd2052.exceptionhandler.response.PlatformNotFoundResponse;
import org.jcd2052.models.Game;
import org.jcd2052.service.games.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/games")
public class ApiGamesController {
    private final GameService gameService;
    private final ModelMapper modelMapper;

    @Autowired
    public ApiGamesController(GameService gameService, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public Set<GameDto> getAllGames() {
        return convertGameCollectionToDto(gameService.getAll());
    }

    @GetMapping("/{platformName}/{gameName}")
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
        dto.getGameInfo().setOtherPlatforms(game.getOtherPlatforms());
        return dto;
    }

    private Set<GameDto> convertGameCollectionToDto(Collection<Game> games) {
        return games.stream()
                .map(this::convertGameToGameDto)
                .collect(Collectors.toSet());
    }
}