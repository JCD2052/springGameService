package org.jcd2052.service;

import org.jcd2052.models.Platform;
import org.jcd2052.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class PlatformService extends BaseService<Platform> {

    @Autowired
    protected PlatformService(PlatformRepository repository) {
        super(repository);
    }

    @Override
    protected void updateById(Platform entityToBeUpdated, Platform entityWithUpdates) {
        entityToBeUpdated.setPlatformName(entityWithUpdates.getPlatformName());
    }
}
