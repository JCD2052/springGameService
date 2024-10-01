package org.jcd2052.api.services;

import org.jcd2052.api.exceptionhandler.exceptions.DeveloperStudioNotFoundException;
import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.repositories.DeveloperStudioRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class DeveloperStudioService extends BaseService<DeveloperStudio> {
    protected DeveloperStudioService(DeveloperStudioRepository repository) {
        super(repository);
    }

    public DeveloperStudio findDeveloperStudioById(long studioId) {
        return repository.findById(studioId).orElseThrow(() -> new DeveloperStudioNotFoundException(studioId));
    }
}
