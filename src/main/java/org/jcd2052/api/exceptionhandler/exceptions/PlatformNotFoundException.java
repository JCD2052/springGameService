package org.jcd2052.api.exceptionhandler.exceptions;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException(long platformId) {
        super("Couldn't find platform with id " + platformId);
    }
}
