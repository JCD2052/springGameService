package org.jcd2052.api.repsonses.exceptionhandler.exception;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException(int platformId) {
        super("Couldn't find platform with id " + platformId);
    }
}
