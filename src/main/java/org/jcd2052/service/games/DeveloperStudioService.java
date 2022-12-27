package org.jcd2052.service.games;

import org.jcd2052.models.DeveloperStudio;
import org.jcd2052.repositories.games.DeveloperStudioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DeveloperStudioService extends BaseService<DeveloperStudio> {
    @Autowired
    protected DeveloperStudioService(DeveloperStudioRepository repository) {
        super(repository);
    }

    @Override
    protected void updateById(DeveloperStudio entityToBeUpdated, DeveloperStudio entityWithUpdates) {
        entityToBeUpdated.setStudioName(entityWithUpdates.getStudioName());

    }
}
