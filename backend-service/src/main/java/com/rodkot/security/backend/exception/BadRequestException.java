package com.rodkot.security.backend.exception;


public class BadRequestException extends BasicLicenceException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }
}
