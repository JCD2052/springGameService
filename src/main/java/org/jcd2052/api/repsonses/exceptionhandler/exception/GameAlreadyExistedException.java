package org.jcd2052.api.repsonses.exceptionhandler.exception;

import org.jcd2052.api.dto.GameDto;

public class GameAlreadyExistedException extends RuntimeException {
    public GameAlreadyExistedException(GameDto gameDto) {
        super(String.format("The game %s is already created.%n%s", gameDto.getGameName(), gameDto));
    }
}
