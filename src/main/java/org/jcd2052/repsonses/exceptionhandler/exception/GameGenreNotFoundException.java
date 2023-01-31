package org.jcd2052.repsonses.exceptionhandler.exception;

public class GameGenreNotFoundException extends RuntimeException {
    public GameGenreNotFoundException(String gameGenreName) {
        super("Couldn't find Game Genre with name " + gameGenreName);
    }
}
