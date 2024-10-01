package org.jcd2052.api.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "game")
public class Game implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "game_info_id")
    private GameInfo gameInfo;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "platform_id")
    private Platform platform;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "developer_studio_id")
    private DeveloperStudio developerStudio;
    @Column(name = "release_date")
    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate releaseDate;
    @OneToMany(mappedBy = "game")
    private Set<GameReview> gameReviews;

    public static Game createGameByIds(
            Long genGameId,
            String gameName,
            Long gameId,
            Long platformId,
            Long genreId,
            Long developerStudioId) {
        Game game = new Game();
        game.setId(gameId);
        Optional.ofNullable(platformId).ifPresent(id -> game.setPlatform(Platform.builder().id(id).build()));

        GameInfo gameInfo = new GameInfo();
        gameInfo.setId(genGameId);
        gameInfo.setGameName(gameName);
        Optional.ofNullable(genreId)
                .ifPresent(gameGenreId -> gameInfo.setGenre(Genre.builder().id(gameGenreId).build()));
        game.setGameInfo(gameInfo);

        Optional.ofNullable(developerStudioId)
                .ifPresent(id -> game.setDeveloperStudio(DeveloperStudio.builder().id(id).build()));
        return game;
    }
}