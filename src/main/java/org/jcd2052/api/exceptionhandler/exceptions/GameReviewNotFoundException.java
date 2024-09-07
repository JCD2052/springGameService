package org.jcd2052.api.exceptionhandler.exceptions;

public class GameReviewNotFoundException extends RuntimeException {
    public GameReviewNotFoundException(int reviewId) {
        super(String.format("Review with id %d has been not found.", reviewId));
    }
}
