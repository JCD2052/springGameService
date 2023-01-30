package org.jcd2052.service.games;

import org.jcd2052.exceptionhandler.exception.PlatformNotFoundException;
import org.jcd2052.models.Platform;
import org.jcd2052.repositories.games.PlatformRepository;
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

    public Platform findPlatformByName(String platformName) {
        return ((PlatformRepository) repository).findPlatformByPlatformName(platformName)
                .orElseThrow(() -> new PlatformNotFoundException(platformName));
    }
}
