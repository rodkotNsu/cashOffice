package com.rodkot.security.backend.exception;

public class MethodArgumentNotUniqueException extends RuntimeException {
    public MethodArgumentNotUniqueException(String message) {
        super(message);
    }
}
