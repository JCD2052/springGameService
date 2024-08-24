package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.entities.GameGenre;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.services.GameGenreService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genres")
@Transactional(readOnly = true)
public class GenresController {
    private static final String APPLICATION_JSON = "application/json";
    private final GameGenreService gameGenreService;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllGenres() {
        return ResponseFactory.createResponse(
                gameGenreService.findAll()
                        .stream()
                        .map(GameGenre::toGenreDto)
                        .toList(),
                HttpStatus.OK);
    }
}
