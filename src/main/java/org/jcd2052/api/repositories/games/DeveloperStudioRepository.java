package org.jcd2052.api.repositories.games;

import org.jcd2052.api.models.DeveloperStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeveloperStudioRepository
        extends JpaRepository<DeveloperStudio, Integer> {

    Optional<DeveloperStudio> findDeveloperStudioByStudioName(String studioName);
}
