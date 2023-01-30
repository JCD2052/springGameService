package org.jcd2052.repositories.games;

import org.jcd2052.models.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<GameGenre, Integer> {
    Optional<GameGenre> findGameGenreByGenreName(String genreName);
}
