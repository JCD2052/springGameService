package org.jcd2052.api.exceptionhandler.exceptions;

public class UserAlreadyCreatedException extends RuntimeException {
    public UserAlreadyCreatedException(String email, String username) {
        super(String.format("The user with %s and %s is already created.", email, username));
    }
}
