package com.fiiadmission.api.exceptions;

public class NotFoundException extends FiiAdmissionGenericException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
