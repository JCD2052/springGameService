package org.jcd2052.exceptionhandler.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class GenericExceptionResponse {
    private final String message;
    private final long timestamp;
}
