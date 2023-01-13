package org.jcd2052.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jcd2052.dto.GameInfoDto;

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
    @Column(name = "game_id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "game_name", nullable = false, length = Integer.MAX_VALUE)
    private String gameName;

    @NotNull
    @Column(name = "game_description", nullable = false, length = Integer.MAX_VALUE)
    private String gameDescription;

    @NotNull
    @Column(name = "game_release_date", nullable = false)
    private int gameReleaseDate;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_genre_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private GameGenre gameGenre;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "game_developer_studio_id", nullable = false)
    @ToString.Exclude
    @JsonManagedReference
    private DeveloperStudio gameDeveloperStudio;

    @OneToMany(mappedBy = "gameInfo", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @JsonBackReference
    private Set<Game> games;

    public GameInfo(GameInfoDto gameInfoDto) {
        this.gameName = gameInfoDto.getName();
        this.gameDescription = gameInfoDto.getDescription();
        this.gameReleaseDate = gameInfoDto.getReleaseYear();
        this.gameGenre = gameInfoDto.getGameGenre();
        this.gameDeveloperStudio = gameInfoDto.getDeveloperStudio();
    }

    public Set<Platform> getAllPlatforms() {
        return games.stream()
                .map(Game::getPlatform)
                .collect(Collectors.toSet());
    }
}