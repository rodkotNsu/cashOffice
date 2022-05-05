package com.rodkot.security.backend.exception;

public class XmlSerializerException extends Exception {
    public XmlSerializerException() {
        super();
    }

    public XmlSerializerException(String message) {
        super(message);
    }

    public XmlSerializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
