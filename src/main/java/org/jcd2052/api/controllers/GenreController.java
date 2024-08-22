package org.jcd2052.api.controllers;

import org.jcd2052.api.entities.GameGenre;
import org.jcd2052.api.entities.GameInfo;
import org.jcd2052.api.factories.GameDtoFactory;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.exceptionhandler.exception.GameGenreNotFoundException;
import org.jcd2052.api.service.games.GameGenreService;
import org.jcd2052.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/genres")
@Transactional(readOnly = true)
public class GenreController {
    private static final String APPLICATION_JSON = "application/json";
    private final GameGenreService gameGenreService;

    @Autowired
    public GenreController(GameGenreService gameGenreService) {
        this.gameGenreService = gameGenreService;
    }

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllGenres() {
        return Utils.createResponse(
                gameGenreService.getAll()
                        .stream()
                        .map(GameGenre::toGenreDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{genreId}/games")
    public ResponseEntity<BaseResponse> getAllGamesByGenreName(@PathVariable int genreId) {
        return Utils.createResponse(GameDtoFactory.createGameDtoList(
                        gameGenreService.findGameGenreByIdOrThrowError(genreId)
                                .getGameInfos()
                                .stream()
                                .map(GameInfo::getGames)
                                .flatMap(Collection::stream)
                                .collect(Collectors.toList())),
                HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleGenreNotFoundException(GameGenreNotFoundException exception) {
        return Utils.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
