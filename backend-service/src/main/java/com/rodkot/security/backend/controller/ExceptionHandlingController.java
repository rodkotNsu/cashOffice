package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.ErrorCode;
import com.rodkot.security.backend.exception.Response;
import com.rodkot.security.backend.exception.BasicLicenceException;
import com.rodkot.security.backend.exception.IODocumentException;
import com.rodkot.security.backend.exception.InsufficientFundsException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@ResponseBody
@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(BasicLicenceException.class)
    public ResponseEntity<Response<Void>> handleLicenceException(BasicLicenceException exception) {
        return getResponseWithError(exception.getErrorCode(), exception);
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response<Void>> handleMaxSizeException(MaxUploadSizeExceededException exception) {
        return getResponseWithError(ErrorCode.PAYLOAD_TOO_LARGE,exception);
    }

    @ExceptionHandler(IODocumentException.class)
    public ResponseEntity<Response<Void>> handleIODocumentException(IODocumentException exception) {
        return getResponseWithError(ErrorCode.INTERNAL_SERVER_ERROR,exception);
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<Response<Void>> handleInsufficientFundsException(InsufficientFundsException exception) {
        return getResponseWithError(ErrorCode.BAD_REQUEST,exception);
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
