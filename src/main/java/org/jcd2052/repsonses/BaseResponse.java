package org.jcd2052.repsonses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
@ToString
public class BaseResponse {
    private final Object response;
    private final LocalDateTime timestamp;

    public BaseResponse(Object response) {
        this.response = response;
        this.timestamp = LocalDateTime.now();
    }
}
