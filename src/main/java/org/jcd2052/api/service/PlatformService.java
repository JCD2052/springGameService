package org.jcd2052.api.service;

import org.jcd2052.api.repsonses.exceptionhandler.exception.PlatformNotFoundException;
import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.repositories.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlatformService extends BaseService<Platform> {
    @Autowired
    protected PlatformService(PlatformRepository repository) {
        super(repository);
    }

    public Platform findPlatformByIdOrThrowError(int platformId) {
        return repository.findById(platformId).orElseThrow(() -> new PlatformNotFoundException(platformId));
    }
}
