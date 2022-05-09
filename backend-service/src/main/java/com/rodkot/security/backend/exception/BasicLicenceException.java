package com.rodkot.security.backend.exception;

import lombok.Getter;

@Getter
public abstract class BasicLicenceException extends RuntimeException {
    private final ErrorCode errorCode;

    protected BasicLicenceException(ErrorCode errorCode) {
        this(errorCode, errorCode.getErrorMessage());
    }

    protected BasicLicenceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
