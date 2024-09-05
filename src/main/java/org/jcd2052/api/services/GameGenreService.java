package org.jcd2052.api.services;

import org.jcd2052.api.exceptions.GameGenreNotFoundException;
import org.jcd2052.api.entities.Genre;
import org.jcd2052.api.repositories.GenreRepository;
import org.springframework.stereotype.Service;

@Service
public class GameGenreService extends BaseService<Genre> {
    protected GameGenreService(GenreRepository repository) {
        super(repository);
    }

    public Genre findGameGenreByIdOrThrowError(int genreId) {
        return repository.findById(genreId).orElseThrow(() -> new GameGenreNotFoundException(genreId));
    }
}