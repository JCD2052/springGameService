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
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jcd2052.api.dto.GameDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.LinkedHashSet;
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

    @Transient
    public GameDto toGameDto() {
        return GameDto.builder()
                .id(id)
                .gameName(gameInfo.getGameName())
                .description(gameInfo.getGameDescription())
                .gameGenre(gameInfo.getGameGenre().getGenreName())
                .platform(platform.getPlatformName())
                .developerStudio(developerStudio.getStudioName())
                .releaseDate(releaseDate)
                .build();
    }
}