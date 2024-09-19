package org.jcd2052.api.exceptionhandler.exceptions;

import org.jcd2052.api.dto.output.GameDto;

public class GameAlreadyExistedException extends RuntimeException {
    public GameAlreadyExistedException(GameDto gameDto) {
        super(String.format("The game %s is already created.%n%s", gameDto.getGameName(), gameDto));
    }
}
