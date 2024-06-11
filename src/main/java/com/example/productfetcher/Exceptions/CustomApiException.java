package com.example.productfetcher.Exceptions;

public class CustomApiException extends RuntimeException {
    public CustomApiException(String message) {
        super(message);
    }

    public static class NoProductsFoundException extends CustomApiException {
        public NoProductsFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateShopperProductException extends CustomApiException {
        public DuplicateShopperProductException(String message) {
            super(message);
        }
    }

    public static class MaxLimiterException extends CustomApiException {
        public MaxLimiterException(String message) {
            super(message);
        }
    }

}