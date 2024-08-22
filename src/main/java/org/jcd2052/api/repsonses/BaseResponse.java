package org.jcd2052.api.repsonses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class BaseResponse {
    private final Object response;
    private final LocalDateTime timestamp;

    public BaseResponse(Object response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }
}
