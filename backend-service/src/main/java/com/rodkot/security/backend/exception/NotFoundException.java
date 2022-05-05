package com.rodkot.security.backend.exception;

import com.rodkot.security.backend.ErrorCode;


public class NotFoundException extends BasicLicenceException {
    public NotFoundException() {
        super(ErrorCode.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(ErrorCode.NOT_FOUND, message);
    }
}
