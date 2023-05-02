package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.controllers.exceptions;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services.exceptions.ContactNotFoundException;

@ControllerAdvice
public class ControllersExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ContactNotFoundException.class})
    public ResponseEntity<Object> handleContactNotFoundException(final RuntimeException exception,
            final WebRequest webRequest) {
        final var errorAtt = ErrorResponseAttributes.builder().message(List.of(exception.getMessage())).build();
        return new ResponseEntity<>(errorAtt, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
            WebRequest request) {
        final var errorAtt = ex.getBindingResult().getAllErrors().stream()
                .map(e -> e.getDefaultMessage()).toList();
        return new ResponseEntity<>(errorAtt, HttpStatus.BAD_REQUEST);
    }


}
