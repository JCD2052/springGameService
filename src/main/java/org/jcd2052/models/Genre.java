package org.jcd2052.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
//TODO should a game have several genres???

@NoArgsConstructor
@Entity(name = "game_genre")
@Data
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "genreId")
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int genreId;
    @Column(name = "genre_name")
    private String genreName;
    @OneToMany(mappedBy = "genre")
    private Set<GameInfo> games;
}
