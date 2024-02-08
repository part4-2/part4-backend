package com.example.demo.global.presentation;

import com.example.demo.review.exception.ReviewException;
import com.example.demo.review.exception.WeatherException;
import com.example.demo.spot.exception.SpotException;
import com.example.demo.user.exception.UserException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class CustomExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(final MethodArgumentNotValidException exception) {
        final String defaultErrorMessage = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        log.warn(() -> defaultErrorMessage);

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(defaultErrorMessage));
    }

    @ExceptionHandler(value = {
            UserException.UserNotFoundException.class,
            ReviewException.ReviewNotFoundException.class,
            SpotException.SpotNotFoundException.class,
            UsernameNotFoundException.class,
            WeatherException.WeatherNotFoundException.class,
    })
    public ResponseEntity<ErrorResponse> handleNotFoundException(final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(message));
    }
    @ExceptionHandler(value = {
            SpotException.DuplicatedSpotExistsException.class
    })
    public ResponseEntity<ErrorResponse> handleCustomBadRequestException(final RuntimeException exception) {
        final String message = exception.getMessage();
        log.warn(message);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(message));
    }
}
