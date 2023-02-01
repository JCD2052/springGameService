package org.jcd2052.api.repsonses.exceptionhandler.exception;

public class GameGenreNotFoundException extends RuntimeException {
    public GameGenreNotFoundException(String gameGenreName) {
        super("Couldn't find Game Genre with name " + gameGenreName);
    }
}
