package org.jcd2052.api.repsonses.exceptionhandler.exception;

public class DeveloperStudioNotFoundException extends RuntimeException {
    public DeveloperStudioNotFoundException(String studioName) {
        super("Couldn't find studio with name " + studioName);
    }
}
