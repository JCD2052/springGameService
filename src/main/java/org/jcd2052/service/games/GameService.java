package org.jcd2052.service.games;

import org.jcd2052.models.GameInfo;
import org.jcd2052.repositories.games.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GameService extends BaseService<GameInfo> {

    @Autowired
    public GameService(GameRepository repository) {
        super(repository);
    }

    @Override
    protected void updateById(GameInfo entityToBeUpdated, GameInfo entityWithUpdates) {
        entityToBeUpdated.setName(entityWithUpdates.getName());
        entityToBeUpdated.setGenre(entityWithUpdates.getGenre());
        entityToBeUpdated.setReleaseYear(entityWithUpdates.getReleaseYear());
        entityToBeUpdated.setDeveloperStudio(entityWithUpdates.getDeveloperStudio());
        entityToBeUpdated.setPlatforms(entityWithUpdates.getPlatforms());
    }
}
