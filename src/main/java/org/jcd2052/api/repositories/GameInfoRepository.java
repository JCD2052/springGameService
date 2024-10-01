package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameInfoRepository extends JpaRepository<GameInfo, Long> {
    Optional<GameInfo> findGameInfoByGameName(String gameName);
}
