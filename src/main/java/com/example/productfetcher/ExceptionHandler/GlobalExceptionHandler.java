package com.example.productfetcher.ExceptionHandler;

import com.example.productfetcher.Exceptions.CustomApiException;
import com.example.productfetcher.Model.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

//Global Exception Handler to handle exceptions globally.
@ControllerAdvice
public class GlobalExceptionHandler {

    //using custom error response format.
    @ExceptionHandler(CustomApiException.NoProductsFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoProductsFoundException(CustomApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(CustomApiException.MaxLimiterException.class)
    public ResponseEntity<ErrorResponse>handleMaxLimiterException(CustomApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    @ExceptionHandler(CustomApiException.DuplicateShopperProductException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateShopperProductException(CustomApiException ex) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setError(HttpStatus.CONFLICT.getReasonPhrase());
        errorResponse.setMessage(ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationExceptions(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        StringBuilder errorMessageBuilder = new StringBuilder("Validation failed: ");
        for (ConstraintViolation<?> violation : violations) {
            errorMessageBuilder.append(violation.getMessage()).append("; ");
        }

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setError(HttpStatus.BAD_REQUEST.getReasonPhrase());
        errorResponse.setMessage(errorMessageBuilder.toString());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }



}