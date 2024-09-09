package org.jcd2052.api.exceptionhandler.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("The user with id %s is not found.".formatted(id));
    }

    public UserNotFoundException(String username, String email) {
        super("The user with the username %s or the email %s is not found".formatted(username, email));
    }
}
