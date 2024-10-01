package org.jcd2052.api.services;

import org.jcd2052.api.entities.GameReview;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewNotFoundException;
import org.jcd2052.api.repositories.GameReviewsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameReviewsService extends BaseService<GameReview> {
    protected GameReviewsService(GameReviewsRepository repository) {
        super(repository);
    }

    public Optional<GameReview> findGameReviewByGameIdAndReviewerUserId(long gameId, long reviewUserId) {
        return ((GameReviewsRepository) repository).findGameReviewByGameIdAndReviewerUserId(gameId, reviewUserId);
    }

    public GameReview findReviewByIdOrThrowError(long reviewId) {
        return repository.findById(reviewId).orElseThrow(() -> new GameReviewNotFoundException(reviewId));
    }

}
