package com.example.productfetcher.ExceptionHandler;

import com.example.productfetcher.Exceptions.CustomDataAccessException;
import com.example.productfetcher.Exceptions.DuplicateShopperProductException;
import com.example.productfetcher.Model.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//Global Exception Hnadler to handle exceptions globally.
@ControllerAdvice
public class GlobalExceptionHandler {

    //using custom error response format.
    @ExceptionHandler(CustomDataAccessException.class)
    public ResponseEntity<ErrorResponse> handleNoProductsFoundException(CustomDataAccessException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(DuplicateShopperProductException.class)
    public ResponseEntity<String> handleDuplicateShopperProductException(DuplicateShopperProductException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }


}