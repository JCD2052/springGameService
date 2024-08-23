package org.jcd2052.api.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.GameReviewDto;
import org.jcd2052.api.entities.GameReview;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GameReviewsDtoFactory {
    public static List<GameReviewDto> createGameReviewDtoList(Collection<GameReview> gameReviews) {
        return gameReviews.stream().map(GameReview::toGameReviewDto).toList();
    }
}
