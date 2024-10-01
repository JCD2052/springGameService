package org.jcd2052.api.exceptionhandler.exceptions;

public class DeveloperStudioNotFoundException extends RuntimeException {
    public DeveloperStudioNotFoundException(long studioId) {
        super("Couldn't find studio with id " + studioId);
    }
}
