package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "game")
@JsonIdentityInfo(scope = Game.class,
        generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "base_game_info_id")
    private GameInfo gameInfo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "platform_id")
    private Platform platform;

    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    private Set<GameRating> gameRatings = new HashSet<>();

    public Game(GameInfo gameInfo, Platform platform) {
        this.gameInfo = gameInfo;
        this.platform = platform;
    }

    public Set<Platform> getOtherPlatforms() {
        return gameInfo.getAllPlatforms().stream()
                .filter(gamePlatform -> !gamePlatform.equals(this.platform))
                .collect(Collectors.toSet());
    }

    public double getAverageRating() {
        return gameRatings.stream()
                .flatMapToInt(gameRating -> IntStream.of(gameRating.getRating()))
                .average()
                .orElse(0.0);
    }
}