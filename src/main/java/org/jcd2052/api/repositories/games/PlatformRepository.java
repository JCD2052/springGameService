package org.jcd2052.api.repositories.games;

import org.jcd2052.api.entities.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
    Optional<Platform> findPlatformByPlatformName(String platformName);
}
