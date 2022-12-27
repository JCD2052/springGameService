package org.jcd2052.repositories.games;

import org.jcd2052.models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Integer> {
}
