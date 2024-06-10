package com.example.productfetcher.Exceptions;

public class DuplicateShopperProductException extends RuntimeException {

    public DuplicateShopperProductException(String message) {
        super(message);
    }
}