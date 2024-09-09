package org.jcd2052.api.repsonses;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseFactory {
    public static <T> ResponseEntity<BaseResponse> createResponse(T entity, HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseResponse(entity), httpStatus);
    }
}
