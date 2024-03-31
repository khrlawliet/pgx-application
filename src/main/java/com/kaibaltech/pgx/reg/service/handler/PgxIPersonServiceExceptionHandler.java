package com.kaibaltech.pgx.reg.service.handler;


import com.kaibaltech.pgx.reg.service.dto.APIResponse;
import com.kaibaltech.pgx.reg.service.dto.ErrorDTO;
import com.kaibaltech.pgx.reg.service.exception.PersonNotFoundException;
import com.kaibaltech.pgx.reg.service.exception.PersonServiceBusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class PgxIPersonServiceExceptionHandler {

    private static final String FAILURE_STATUS = "FAILED";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<Object> handleMethodArgumentException(
            MethodArgumentNotValidException exception) {
        List<ErrorDTO> errors = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> new ErrorDTO(error.getField(),
                        error.getDefaultMessage()))
                .toList();
        return APIResponse
                .builder()
                .status(FAILURE_STATUS)
                .errors(errors)
                .build();
    }

    @ExceptionHandler(PersonServiceBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<Object> handleServiceException(
            PersonServiceBusinessException exception) {
        return APIResponse
                .builder()
                .status(FAILURE_STATUS)
                .errors(Collections.singletonList(
                        new ErrorDTO("", exception.getMessage())))
                .build();
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse<Object> handlePersonNotFoundException(
            PersonNotFoundException exception) {
        return APIResponse
                .builder()
                .status(FAILURE_STATUS)
                .errors(Collections.singletonList(
                        new ErrorDTO("", exception.getMessage())))
                .build();
    }
}
