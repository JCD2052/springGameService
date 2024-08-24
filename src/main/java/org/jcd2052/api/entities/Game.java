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
import java.util.LinkedHashSet;
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
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
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
    private Set<GameReview> gameReviews = new LinkedHashSet<>();

    public static Game createGameByIds(
            Integer genGameId,
            Integer gameId,
            Integer platformId,
            Integer genreId,
            Integer developerStudioId) {
        Game gameProbe = new Game();
        Optional.ofNullable(gameId).ifPresent(gameProbe::setId);
        Optional.ofNullable(platformId).ifPresent(id -> gameProbe.setPlatform(Platform.builder().id(id).build()));
        Optional.ofNullable(genGameId).ifPresent(id -> {
            GameInfo gameInfo = new GameInfo();
            gameInfo.setId(id);
            Optional.ofNullable(genreId)
                    .ifPresent(gameGenreId -> gameInfo.setGameGenre(GameGenre.builder().id(gameGenreId).build()));
            gameProbe.setGameInfo(gameInfo);
        });
        Optional.ofNullable(developerStudioId)
                .ifPresent(id -> gameProbe.setDeveloperStudio(DeveloperStudio.builder().id(id).build()));
        return gameProbe;
    }
}