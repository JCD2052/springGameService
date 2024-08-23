package org.jcd2052.api.services;

import org.jcd2052.api.entities.GameReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class GameReviewsService extends BaseService<GameReview> {
    protected GameReviewsService(JpaRepository<GameReview, Integer> repository) {
        super(repository);
    }
}
