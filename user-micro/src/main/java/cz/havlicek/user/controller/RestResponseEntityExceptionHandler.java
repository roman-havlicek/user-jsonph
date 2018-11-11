package cz.havlicek.user.controller;

import cz.havlicek.user.exception.UserNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * Basic error handling for HTTP responses.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(UserNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), new HttpHeaders(), ex.getStatus());
    }
}
