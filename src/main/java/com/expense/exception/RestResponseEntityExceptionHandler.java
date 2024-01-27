package com.expense.exception;

import com.expense.dto.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<Object> handleServerError(
            RuntimeException ex, HttpServletRequest request) {


        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());
        return ResponseHandler.generateResponse(HttpStatus.INTERNAL_SERVER_ERROR.series().toString(), HttpStatus.INTERNAL_SERVER_ERROR, null);

    }


    @ExceptionHandler(value = {BadRequestException.class, Exception.class})
    public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getRequestURI());

        return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST.series().toString(), HttpStatus.BAD_REQUEST, error);
    }


}