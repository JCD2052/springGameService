package org.jcd2052.api.service.games;

import org.jcd2052.api.repsonses.exceptionhandler.exception.GameGenreNotFoundException;
import org.jcd2052.api.models.GameGenre;
import org.jcd2052.api.repositories.games.GenreRepository;
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

    public GameGenre findGameGenreByGenreName(String genreName) {
        return ((GenreRepository) repository).findGameGenreByGenreName(genreName)
                .orElseThrow(() -> new GameGenreNotFoundException(genreName));
    }
}