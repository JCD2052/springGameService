package org.jcd2052.api.exceptionhandler.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(long id) {
        super(String.format("Couldn't find a game with id %d", id));
    }
}
