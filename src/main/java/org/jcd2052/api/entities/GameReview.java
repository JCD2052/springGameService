package org.jcd2052.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Generated;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Optional;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_review")
public class GameReview implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer_user_id", nullable = false)
    private User reviewerUser;
    @NotNull
    @Column(name = "score", nullable = false)
    private Double score;
    @NotNull
    @Column(name = "review_comment", nullable = false, length = Integer.MAX_VALUE)
    private String reviewComment;
    @Column(name = "time_created", nullable = false)
    @Generated
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timeCreated;
    @Column(name = "time_updated", nullable = false)
    @Generated
    @Temporal(value = TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private LocalDateTime timeUpdated;

    public static GameReview createGameReviewFromIds(
            Integer reviewId,
            Integer userId,
            Integer gameId) {
        GameReview gameReview = new GameReview();
        gameReview.setId(reviewId);
        Optional.ofNullable(gameId).ifPresent(id -> gameReview.setGame(Game.builder().id(id).build()));
        Optional.ofNullable(userId).ifPresent(id -> gameReview.setReviewerUser(User.builder().id(id).build()));
        return gameReview;
    }
}