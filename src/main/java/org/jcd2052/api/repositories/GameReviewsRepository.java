package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameReviewsRepository extends JpaRepository<GameReview, Integer> {
    Optional<GameReview> findGameReviewByGameIdAndReviewerUserId(int gameId, int reviewUserId);
}
