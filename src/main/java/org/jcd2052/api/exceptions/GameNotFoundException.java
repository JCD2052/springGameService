package org.jcd2052.api.exceptions;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(int id) {
        super(String.format("Couldn't find a game with id %d", id));
    }
}
