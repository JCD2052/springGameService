package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> findGameByGameInfoGameNameAndPlatformId(String gameName, long platformId);
}