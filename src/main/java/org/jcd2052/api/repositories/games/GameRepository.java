package org.jcd2052.api.repositories.games;

import org.jcd2052.api.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findGameByPlatformPlatformNameAndGameInfoGameName(String platformName, String gameName);

    Set<Game> findAllByGameInfoGameGenreGenreName(String genreName);

    Set<Game> findAllByGameInfoGameDeveloperStudioStudioName(String developerStudioName);

    Set<Game> findAllByPlatformPlatformName(String platformName);

    Set<Game> findAllByGameInfoGameReleaseDate(int releaseData);
}