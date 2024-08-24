package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.factories.GameReviewsDtoFactory;
import org.jcd2052.api.repositories.GameReviewsRepository;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/gameReviews")
@Transactional
public class GameReviewController {
    private static final String APPLICATION_JSON = "application/json";
    private final GameReviewsRepository gameReviewsRepository;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllReviews() {
        return ResponseFactory.createResponse(
                GameReviewsDtoFactory.createGameReviewDtoList(gameReviewsRepository.findAll()),
                HttpStatus.OK);
    }

    @GetMapping(produces = APPLICATION_JSON, value = "/{gameId}")
    public ResponseEntity<BaseResponse> getGameReviewsByGameName(@PathVariable int gameId) {
        return ResponseFactory.createResponse(
                GameReviewsDtoFactory.createGameReviewDtoList(gameReviewsRepository.findAllByGameId(gameId)),
                HttpStatus.OK);
    }
}
