package org.jcd2052.api.exceptionhandler;

import io.jsonwebtoken.JwtException;
import lombok.extern.log4j.Log4j2;
import org.jcd2052.api.exceptionhandler.exceptions.DeveloperStudioNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.GameAlreadyExistedException;
import org.jcd2052.api.exceptionhandler.exceptions.GameGenreNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.GameNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewExistsException;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.GameReviewScoreException;
import org.jcd2052.api.exceptionhandler.exceptions.PlatformNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.PostCommentNotFoundException;
import org.jcd2052.api.exceptionhandler.exceptions.PostNotFoundExecution;
import org.jcd2052.api.exceptionhandler.exceptions.UserAlreadyCreatedException;
import org.jcd2052.api.exceptionhandler.exceptions.UserNotFoundException;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleSecurityException(Exception exception) {
        log.error(exception.getMessage(), exception);

        if (exception instanceof AuthenticationException) {
            return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        if (exception instanceof AccessDeniedException) {
            return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
        }

        if (exception instanceof JwtException) {
            return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.FORBIDDEN);
        }
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handlePostNotFoundExecution(PostNotFoundExecution exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handlePostCommentNotFoundException(PostCommentNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleUserAlreadyCreatedException(UserAlreadyCreatedException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handeGameReviewNotFoundException(GameReviewNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handeGameReviewExistsException(GameReviewExistsException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handeGameReviewScoreException(GameReviewScoreException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleGenreNotFoundException(GameGenreNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handeDeveloperStudioNotFoundException(
            DeveloperStudioNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleGameNotFoundException(GameNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleGameAlreadyExistedException(GameAlreadyExistedException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handlePlatformNotFoundException(PlatformNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<BaseResponse> handleDuplicateException(SQLException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
