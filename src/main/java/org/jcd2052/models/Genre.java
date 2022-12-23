package org.jcd2052.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
//TODO should a game have several genres???

@NoArgsConstructor
@Entity(name = "game_genre")
@Data
@ToString(exclude = "games")
@EqualsAndHashCode(exclude = "games")
public class Genre {
    @Id
    @Column(name = "genre_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int genreId;
    @Column(name = "genre_name")
    private String genreName;
    @OneToMany(mappedBy = "genre")
    private Set<GameInfo> games;
}
