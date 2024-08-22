package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.jcd2052.api.dto.GenreDto;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "genre")
public class GameGenre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "genre_name", unique = true)
    private String genreName;

    @OneToMany(mappedBy = "gameGenre")
    @ToString.Exclude
    @JsonIgnore
    private Set<GameInfo> gameInfos;

    @Transient
    public GenreDto toGenreDto() {
        return GenreDto.builder()
                .id(id)
                .genreName(genreName)
                .build();
    }
}