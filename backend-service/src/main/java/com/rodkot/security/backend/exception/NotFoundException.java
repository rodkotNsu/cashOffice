package com.rodkot.security.backend.exception;


public class NotFoundException extends BasicLicenceException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }
}
