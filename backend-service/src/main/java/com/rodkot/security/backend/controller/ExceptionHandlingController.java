package com.rodkot.security.backend.controller;

import com.rodkot.security.backend.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return getResponseWithError(ErrorCode.BAD_REQUEST, exception);
    }

    @ExceptionHandler(MethodArgumentNotUniqueException.class)
    public ResponseEntity<Response<Void>> handleMethodArgumentNonUniqueException(MethodArgumentNotUniqueException exception) {
        return getResponseWithError(ErrorCode.BAD_REQUEST, exception);
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
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Response<Void>> handleConstraintViolationException(DataIntegrityViolationException exception) {
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
