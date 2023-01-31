package org.jcd2052.repsonses.exceptionhandler.exception;

public class PlatformNotFoundException extends RuntimeException {
    public PlatformNotFoundException(String platformName) {
        super("Couldn't find platform " + platformName);
    }
}
