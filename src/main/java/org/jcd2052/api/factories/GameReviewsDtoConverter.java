package org.jcd2052.api.factories;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.dto.GameReviewDto;
import org.jcd2052.api.entities.GameReview;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@RequiredArgsConstructor
@Component
public class GameReviewsDtoConverter implements DtoEntityConverter<GameReview, GameReviewDto> {
    private final GameDtoConverter gameDtoConverter;
    private final UserDtoConverter userDtoConverter;

    @Override
    public GameReviewDto convertToDto(GameReview entity) {
        return GameReviewDto.builder()
                .id(entity.getId())
                .game(gameDtoConverter.convertToDto(entity.getGame()))
                .user(userDtoConverter.convertToDto(entity.getReviewerUser()))
                .score(entity.getScore())
                .comment(entity.getReviewComment())
                .timeCreated(entity.getTimeCreated())
                .timeUpdated(entity.getTimeUpdated())
                .build();
    }

    @Override
    public Comparator<GameReviewDto> createDtoComparator() {
        return Comparator.comparing(GameReviewDto::getScore);
    }
}
