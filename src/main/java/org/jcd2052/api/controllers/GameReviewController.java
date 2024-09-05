package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.dto.GameReviewDtoInput;
import org.jcd2052.api.dto.GameReviewDtoUpdateInput;
import org.jcd2052.api.entities.GameReview;
import org.jcd2052.api.exceptions.GameReviewExistsException;
import org.jcd2052.api.exceptions.GameReviewScoreException;
import org.jcd2052.api.factories.GameReviewsDtoConverter;
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
public class GameReviewController {
    private static final String APPLICATION_JSON = "application/json";
    private static final double MIN_SCORE = 0;
    private static final double MAX_SCORE = 10;
    private final GameReviewsService gameReviewsService;
    private final UserService userService;
    private final GameService gameService;
    private final GameReviewsDtoConverter gameReviewsDtoConverter;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> fetchGameReviews(
            @RequestParam(required = false) Integer reviewId,
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) Integer gameId) {
        GameReview gameReviewProbe = GameReview.createGameReviewFromIds(reviewId, userId, gameId);
        return ResponseFactory.createResponse(
                gameReviewsDtoConverter.createDtoListFromEntities(
                        gameReviewsService.fetchEntities(gameReviewProbe)),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping(produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> createReview(@RequestBody GameReviewDtoInput input) {
        Double score = input.getScore();
        validateScore(score);

        int gameId = input.getGameId();
        int userReviewerId = input.getUserReviewerId();

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

    @Transactional
    @PutMapping(produces = APPLICATION_JSON, consumes = APPLICATION_JSON, value = "/{reviewId}")
    public ResponseEntity<BaseResponse> updateReview(
            @PathVariable int reviewId,
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

    @Transactional
    @DeleteMapping(produces = APPLICATION_JSON, value = "/{reviewId}")
    public ResponseEntity<BaseResponse> deleteReview(@PathVariable int reviewId) {
        GameReview gameReview = gameReviewsService.findReviewByIdOrThrowError(reviewId);
        gameReviewsService.delete(gameReview);
        return ResponseFactory.createResponse(
                "Review %d has been deleted successfully".formatted(reviewId),
                HttpStatus.OK);
    }

    private static void validateScore(Double score) {
        Optional.ofNullable(score).ifPresentOrElse(value -> {
            if (value < MIN_SCORE || value > MAX_SCORE) {
                throw new GameReviewScoreException(score);
            }
        }, () -> {
            throw new GameReviewScoreException();
        });
    }
}
