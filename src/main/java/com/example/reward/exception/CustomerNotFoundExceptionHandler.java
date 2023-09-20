package com.example.reward.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomerNotFoundExceptionHandler {

    public ResponseEntity<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
        CustomerNotFoundExceptionResponse customerNotFoundExceptionResponse = new CustomerNotFoundExceptionResponse(
                e.getMessage(),
                e,
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(customerNotFoundExceptionResponse, HttpStatus.NOT_FOUND);
    }
}
