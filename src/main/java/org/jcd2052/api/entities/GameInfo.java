package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "base_game_info")
public class GameInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    private Integer id;

    @Column(name = "game_name")
    private String gameName;

    @Column(name = "game_description")
    private String gameDescription;

    @Column(name = "game_release_date")
    private int gameReleaseDate;

    @ManyToOne
    @JoinColumn(name = "game_genre_id")
    private GameGenre gameGenre;

    @ManyToOne
    @JoinColumn(name = "game_developer_studio_id")
    private DeveloperStudio gameDeveloperStudio;

    @OneToMany(mappedBy = "gameInfo", fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @ToString.Exclude
    @JsonBackReference
    private Set<Game> games = new HashSet<>();

    public GameInfo(String gameName, String gameDescription, int gameReleaseDate,
                    GameGenre gameGenre, DeveloperStudio gameDeveloperStudio) {
        this.gameName = gameName;
        this.gameDescription = gameDescription;
        this.gameReleaseDate = gameReleaseDate;
        this.gameGenre = gameGenre;
        this.gameDeveloperStudio = gameDeveloperStudio;
    }

    public Set<Platform> getAllPlatforms() {
        return games.stream()
                .map(Game::getPlatform)
                .collect(Collectors.toSet());
    }

    public void setGames(Set<Game> games) {
        if (this.games == null) {
            this.games = games;
        } else if (this.games != games) {
            this.games.clear();
            if (games != null) {
                this.games.addAll(games);
            }
        }
    }
}