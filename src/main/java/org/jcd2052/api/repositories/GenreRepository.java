package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.GameGenre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GameGenre, Integer> {
}
