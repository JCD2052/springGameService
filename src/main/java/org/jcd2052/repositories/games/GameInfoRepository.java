package org.jcd2052.repositories.games;

import org.jcd2052.models.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameInfoRepository extends JpaRepository<GameInfo, Integer> {
    Optional<GameInfo> findGameInfoByGameName(String gameName);
}
