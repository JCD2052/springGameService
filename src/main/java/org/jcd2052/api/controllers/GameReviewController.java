package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.constants.GameReviewConstants;
import org.jcd2052.api.dto.input.GameReviewDtoInput;
import org.jcd2052.api.dto.input.GameReviewDtoUpdateInput;
import org.jcd2052.api.dto.output.AuthTokenDto;
import org.jcd2052.api.entities.GameReview;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewExistsException;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewScoreException;
import org.jcd2052.api.dtoconverters.GameReviewsDtoConverter;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.jcd2052.api.services.GameReviewsService;
import org.jcd2052.api.services.GameService;
import org.jcd2052.api.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gameReviews")
@Tag(name = "Review")
public class GameReviewController {
    private final GameReviewsService gameReviewsService;
    private final UserService userService;
    private final GameService gameService;
    private final GameReviewsDtoConverter gameReviewsDtoConverter;

    @Operation(summary = "Fetch game review records")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(schema = @Schema(name = "Authentication response",
                            implementation = AuthTokenDto.class),
                            mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)}),
            @ApiResponse(
                    description = "Response when authentication failed",
                    responseCode = "404",
                    content = {@Content(mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)})})
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> fetchGameReviews(
            @RequestParam(required = false) Long reviewId,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long gameId) {
        GameReview gameReviewProbe = GameReview.createGameReviewFromIds(reviewId, userId, gameId);
        return ResponseFactory.createResponse(
                gameReviewsDtoConverter.createDtoListFromEntities(
                        gameReviewsService.fetchEntities(gameReviewProbe)),
                HttpStatus.OK);
    }

    @Operation(summary = "Create game review")
    @Transactional
    @PostMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE, consumes = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> createReview(@RequestBody GameReviewDtoInput input) {
        Double score = input.getScore();
        validateScore(score);

        long gameId = input.getGameId();
        long userReviewerId = input.getUserReviewerId();

        Optional<GameReview> gameReviewByGameIdAndReviewerUserId =
                gameReviewsService.findGameReviewByGameIdAndReviewerUserId(gameId, userReviewerId);

        if (gameReviewByGameIdAndReviewerUserId.isEmpty()) {
            GameReview gameReview = GameReview.builder()
                    .reviewerUser(userService.findByIdOrThrowError(userReviewerId))
                    .game(gameService.getGameByIdOrThrowError(gameId))
                    .reviewComment(Optional.ofNullable(input.getComment()).orElse(StringUtils.EMPTY))
                    .score(score)
                    .build();
            gameReviewsService.save(gameReview);
            return ResponseFactory.createResponse(
                    gameReviewsDtoConverter.convertToDto(gameReview),
                    HttpStatus.CREATED);
        }
        throw new GameReviewExistsException(
                gameReviewsDtoConverter.convertToDto(gameReviewByGameIdAndReviewerUserId.get()));
    }

    @Operation(summary = "Update the certain game review")
    @Transactional
    @PutMapping(
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{reviewId}")
    public ResponseEntity<BaseResponse> updateReview(
            @PathVariable long reviewId,
            @RequestBody GameReviewDtoUpdateInput input) {
        GameReview gameReview = gameReviewsService.findReviewByIdOrThrowError(reviewId);
        Double score = input.getScore();
        validateScore(score);

        gameReview.setScore(score);
        gameReview.setReviewComment(Optional.ofNullable(input.getComment()).orElse(StringUtils.EMPTY));
        gameReview.setTimeUpdated(LocalDateTime.now());

        gameReviewsService.save(gameReview);
        return ResponseFactory.createResponse(gameReviewsDtoConverter.convertToDto(gameReview), HttpStatus.OK);
    }

    @Operation(summary = "Delete the certain game review")
    @Transactional
    @DeleteMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE, value = "/{reviewId}")
    public ResponseEntity<BaseResponse> deleteReview(@PathVariable long reviewId) {
        GameReview gameReview = gameReviewsService.findReviewByIdOrThrowError(reviewId);
        gameReviewsService.delete(gameReview);
        return ResponseFactory.createResponse(
                "Review %d has been deleted successfully".formatted(reviewId),
                HttpStatus.OK);
    }

    private static void validateScore(Double score) {
        Optional.ofNullable(score).ifPresentOrElse(value -> {
            if (value < GameReviewConstants.MIN_SCORE || value > GameReviewConstants.MAX_SCORE) {
                throw new GameReviewScoreException(score);
            }
        }, () -> {
            throw new GameReviewScoreException();
        });
    }
}
