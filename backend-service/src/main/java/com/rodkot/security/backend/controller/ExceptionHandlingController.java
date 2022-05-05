package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.ErrorCode;
import com.rodkot.security.backend.Response;
import com.rodkot.security.backend.exception.BasicLicenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BasicLicenceException.class)
    public ResponseEntity<Response<Void>> handleLicenceException(BasicLicenceException exception) {
        return getResponseWithError(exception.getErrorCode(), exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<Void>> handleError(Exception exception) {
        return getResponseWithError(ErrorCode.INTERNAL_SERVER_ERROR, exception);
    }

    private ResponseEntity<Response<Void>> getResponseWithError(ErrorCode errorCode, Exception exception) {
        log.error("Caught exception: {}", exception.getMessage(), exception);

        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(Response.withError(errorCode, exception.getMessage()));
    }
}
