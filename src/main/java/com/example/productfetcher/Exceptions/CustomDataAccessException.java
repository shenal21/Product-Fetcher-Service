package com.example.productfetcher.Exceptions;

public class CustomDataAccessException  extends RuntimeException {
    public CustomDataAccessException(String message) {
        super(message);
    }

    public static CustomDataAccessException noProductsFoundException(String Message) {
        return new CustomDataAccessException(Message);
    }

}