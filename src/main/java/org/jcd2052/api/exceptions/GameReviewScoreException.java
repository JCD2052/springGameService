package org.jcd2052.api.exceptions;

public class GameReviewScoreException extends RuntimeException {
    public GameReviewScoreException(Double score) {
        super(String.format("Score should be in range from 0 to 10. Received value %f.", score));
    }
}
