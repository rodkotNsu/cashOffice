package com.rodkot.security.backend.exception;

import com.rodkot.security.backend.ErrorCode;


public class BadRequestException extends BasicLicenceException {
    public BadRequestException() {
        super(ErrorCode.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(ErrorCode.BAD_REQUEST, message);
    }
}
