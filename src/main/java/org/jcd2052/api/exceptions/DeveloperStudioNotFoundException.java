package org.jcd2052.api.exceptions;

public class DeveloperStudioNotFoundException extends RuntimeException {
    public DeveloperStudioNotFoundException(int studioId) {
        super("Couldn't find studio with id " + studioId);
    }
}
