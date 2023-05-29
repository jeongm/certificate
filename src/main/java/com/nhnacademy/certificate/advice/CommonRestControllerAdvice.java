package com.nhnacademy.certificate.advice;

import com.nhnacademy.certificate.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CommonRestControllerAdvice {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler({FamilyRelationshipNotFoundException.class,
            HouseholdMovementAddressNotFoundException.class,
            HouseholdNotFoundException.class,
            ResidentNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> notFoundError(RuntimeException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND,exception.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ValidationFailedException.class,
            ResidentDuplicateException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ExceptionResponse> internalServerError(RuntimeException exception){
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
