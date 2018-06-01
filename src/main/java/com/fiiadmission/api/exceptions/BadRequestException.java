package com.fiiadmission.api.exceptions;

public class BadRequestException extends FiiAdmissionGenericException {
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
