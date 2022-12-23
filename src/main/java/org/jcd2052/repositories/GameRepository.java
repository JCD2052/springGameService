package org.jcd2052.repositories;

import org.jcd2052.models.GameInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<GameInfo, Integer> {
}
