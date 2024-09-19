package org.jcd2052.api.exceptionhandler.exceptions;

import org.jcd2052.api.dto.output.GameReviewDto;

public class GameReviewExistsException extends RuntimeException {
    public GameReviewExistsException(GameReviewDto gameReviewDto) {
        super(String.format(
                "The review for game %s: on %s from user %s -- id: %d is already existed.",
                gameReviewDto.getGame().getGameName(),
                gameReviewDto.getGame().getPlatform(),
                gameReviewDto.getUser().getUsername(),
                gameReviewDto.getUser().getId()));
    }
}
