package org.jcd2052.api.repositories;

import org.jcd2052.api.entities.DeveloperStudio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperStudioRepository extends JpaRepository<DeveloperStudio, Long> {
}
