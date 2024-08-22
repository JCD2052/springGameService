package org.jcd2052.api.service;

import org.jcd2052.api.repsonses.exceptionhandler.exception.DeveloperStudioNotFoundException;
import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.repositories.DeveloperStudioRepository;
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

    public DeveloperStudio findDeveloperStudioById(int studioId) {
        return repository.findById(studioId).orElseThrow(() -> new DeveloperStudioNotFoundException(studioId));
    }
}
