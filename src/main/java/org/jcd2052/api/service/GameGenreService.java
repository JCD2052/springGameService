package org.jcd2052.api.service;

import org.jcd2052.api.repsonses.exceptionhandler.exception.GameGenreNotFoundException;
import org.jcd2052.api.entities.GameGenre;
import org.jcd2052.api.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class GameGenreService extends BaseService<GameGenre> {
    @Autowired
    protected GameGenreService(GenreRepository repository) {
        super(repository);
    }

    public GameGenre findGameGenreByIdOrThrowError(int genreId) {
        return repository.findById(genreId).orElseThrow(() -> new GameGenreNotFoundException(genreId));
    }
}