package org.jcd2052.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "game_genre")
public class GameGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "genre_name", nullable = false, length = Integer.MAX_VALUE)
    private String genreName;

    @OneToMany(mappedBy = "gameGenre")
    @ToString.Exclude
    @JsonBackReference(value = "genreGameInfos")
    private Set<GameInfo> gameInfos = new HashSet<>();
}