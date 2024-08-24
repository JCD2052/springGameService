package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.entities.Genre;
import org.jcd2052.api.factories.GenreDtoConverter;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.services.GameGenreService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genres")
@Transactional(readOnly = true)
public class GenresController {
    private static final String APPLICATION_JSON = "application/json";
    private final GameGenreService gameGenreService;
    private final GenreDtoConverter genreDtoConverter;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> fetchGenres(
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) String genreName) {
        Genre genreProbe = Genre.createGameGenre(genreId, genreName);
        return ResponseFactory.createResponse(
                genreDtoConverter.createDtoListFromEntities(gameGenreService.fetchEntities(genreProbe)),
                HttpStatus.OK);
    }
}
