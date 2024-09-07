package org.jcd2052.api.exceptionhandler.exceptions;

import org.jcd2052.api.dto.UserDto;

public class UserAlreadyCreatedException extends RuntimeException {
    public UserAlreadyCreatedException(String email, String username) {
        super(String.format("The user with %s and %s is already created.", email, username));
    }

    public UserAlreadyCreatedException(UserDto userDto) {
        super(String.format("The user is already created.%n%s", userDto));
    }
}
