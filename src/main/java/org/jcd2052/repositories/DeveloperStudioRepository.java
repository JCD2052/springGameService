package org.jcd2052.repositories;

import org.jcd2052.models.DeveloperStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperStudioRepository
        extends JpaRepository<DeveloperStudio, Integer> {
}
