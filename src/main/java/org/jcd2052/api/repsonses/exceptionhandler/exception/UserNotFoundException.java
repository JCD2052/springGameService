package org.jcd2052.api.repsonses.exceptionhandler.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super(String.format("User with id %s is not found.", id));
    }
}
