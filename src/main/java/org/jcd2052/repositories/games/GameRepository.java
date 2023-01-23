package org.jcd2052.repositories.games;

import org.jcd2052.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    Optional<Game> findGameByPlatformPlatformNameAndGameInfoGameName(String platformName,
                                                                     String gameName);

    @Query("select avg (game_rating.rating) from GameRating game_rating " +
            "join Game game on game.id = game_rating.game.id " +
            "join GameInfo game_info on game.gameInfo.id = game_info.id " +
            "join Platform platform on game.platform.id = platform.id " +
            "WHERE game_info.gameName = :gameName " +
            "AND platform.platformName = :platformName")
    Optional<Double> getGameRating(@Param("gameName") String gameName,
                                   @Param("platformName") String platformName);

    Set<Game> findAllByGameInfoGameGenreGenreName(String genreName);

    Set<Game> findAllByGameInfoGameDeveloperStudioStudioName(String developerStudioName);
}