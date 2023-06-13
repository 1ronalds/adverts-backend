package com.advert.handler;

import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorModel> handleInvalidDataValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errors = StringUtils.join(ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList())); // Collects validation errors

        ErrorModel errorModel = new ErrorModel(LocalDate.now(), 400,
                "BAD_REQUEST", errors, request.getRequestURI());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    //@ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorModel> handleServerError(Exception ex, HttpServletRequest request) {
        ErrorModel errorModel = new ErrorModel(LocalDate.now(), 500,
                "INTERNAL_SERVER_ERROR", "Something has gone wrong", request.getRequestURI());
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataException.class)
    protected ResponseEntity<ErrorModel> handleInvalidData(Exception ex, HttpServletRequest request) {
        ErrorModel errorModel = new ErrorModel(LocalDate.now(), 500,
                "BAD_REQUEST", "Malformed data", request.getRequestURI());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUsernamePasswordException.class)
    protected ResponseEntity<ErrorModel> handleInvalidLogin(Exception ex, HttpServletRequest request) {
        ErrorModel errorModel = new ErrorModel(LocalDate.now(), 500,
                "BAD_REQUEST", "Incorrect username/password", request.getRequestURI());
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }
}
