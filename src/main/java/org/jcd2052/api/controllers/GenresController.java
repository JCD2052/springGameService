package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.output.DeveloperStudioDto;
import org.jcd2052.api.dto.output.GenreDto;
import org.jcd2052.api.entities.Genre;
import org.jcd2052.api.dtoconverters.GenreDtoConverter;
import org.jcd2052.api.services.GameGenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genres")
@Transactional(readOnly = true)
@Tag(name = "Genres")
public class GenresController {
    private final GameGenreService gameGenreService;
    private final GenreDtoConverter genreDtoConverter;

    @Operation(summary = "Fetch game genre records")
    @ApiResponses({
            @ApiResponse(
                    description = "Fetch game genres by parameters or returns an empty list",
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = DeveloperStudioDto.class),
                            mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)})})
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<List<GenreDto>> fetchGenres(
            @RequestParam(required = false) Integer genreId,
            @RequestParam(required = false) String genreName) {
        Genre genreProbe = Genre.createGameGenre(genreId, genreName);
        return ResponseEntity.ok(
                genreDtoConverter.createDtoListFromEntities(gameGenreService.fetchEntities(genreProbe)));
    }
}
