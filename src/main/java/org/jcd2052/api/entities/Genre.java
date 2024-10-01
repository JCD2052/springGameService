package org.jcd2052.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@Entity
@Table(name = "genre")
public class Genre implements IEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "genre_name", unique = true)
    private String genreName;

    public static Genre createGameGenre(Long genreId, String genreName) {
        return Genre.builder()
                .id(genreId)
                .genreName(genreName)
                .build();
    }
}