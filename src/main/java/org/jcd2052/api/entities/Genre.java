package org.jcd2052.api.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre {
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

    public static Genre createGameGenre(Integer genreId, String genreName) {
        return Genre.builder()
                .id(genreId)
                .genreName(genreName)
                .build();

    }
}