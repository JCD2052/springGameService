package org.jcd2052.api.repsonses.exceptionhandler.exception;

public class DeveloperStudioNotFoundException extends RuntimeException {
    public DeveloperStudioNotFoundException(int studioId) {
        super("Couldn't find studio with id " + studioId);
    }
}
