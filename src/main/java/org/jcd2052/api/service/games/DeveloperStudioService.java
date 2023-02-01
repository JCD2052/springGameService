package org.jcd2052.api.service.games;

import org.jcd2052.api.repsonses.exceptionhandler.exception.DeveloperStudioNotFoundException;
import org.jcd2052.api.models.DeveloperStudio;
import org.jcd2052.api.repositories.games.DeveloperStudioRepository;
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

    public DeveloperStudio findDeveloperStudioByStudioName(String studioName) {
        return ((DeveloperStudioRepository) repository)
                .findDeveloperStudioByStudioName(studioName)
                .orElseThrow(() -> new DeveloperStudioNotFoundException(studioName));
    }

}
