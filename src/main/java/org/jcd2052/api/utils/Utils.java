package org.jcd2052.api.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jcd2052.api.repsonses.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utils {
    public static <T> ResponseEntity<BaseResponse> createResponse(T entity, HttpStatus httpStatus) {
        return new ResponseEntity<>(new BaseResponse(entity), httpStatus);
    }
}
