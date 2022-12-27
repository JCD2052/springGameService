package org.jcd2052.service.games;

import org.jcd2052.models.Genre;
import org.jcd2052.repositories.games.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GenreService extends BaseService<Genre> {
    @Autowired
    protected GenreService(GenreRepository repository) {
        super(repository);
    }

    @Override
    protected void updateById(Genre entityToBeUpdated, Genre entityWithUpdates) {
        entityToBeUpdated.setGenreName(entityWithUpdates.getGenreName());

    }
}