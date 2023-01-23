package org.jcd2052.exceptionhandler.exception;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException(String gameName, String platformName) {
        super(String.format("Couldn't find game %s on platform %s",
                gameName, platformName));
    }
}
