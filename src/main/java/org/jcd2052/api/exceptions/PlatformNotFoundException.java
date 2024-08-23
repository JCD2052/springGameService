package org.jcd2052.api.exceptions;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException(int platformId) {
        super("Couldn't find platform with id " + platformId);
    }
}
