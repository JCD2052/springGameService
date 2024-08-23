package org.jcd2052.api.services;

import org.jcd2052.api.exceptions.GameGenreNotFoundException;
import org.jcd2052.api.entities.GameGenre;
import org.jcd2052.api.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameGenreService extends BaseService<GameGenre> {
    @Autowired
    protected GameGenreService(GenreRepository repository) {
        super(repository);
    }

    public GameGenre findGameGenreByIdOrThrowError(int genreId) {
        return repository.findById(genreId).orElseThrow(() -> new GameGenreNotFoundException(genreId));
    }
}