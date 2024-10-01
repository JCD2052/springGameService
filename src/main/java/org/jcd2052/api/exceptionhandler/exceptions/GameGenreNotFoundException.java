package org.jcd2052.api.exceptionhandler.exceptions;

public class GameGenreNotFoundException extends RuntimeException {
    public GameGenreNotFoundException(long genreId) {
        super("Couldn't find Game Genre with id " + genreId);
    }
}
