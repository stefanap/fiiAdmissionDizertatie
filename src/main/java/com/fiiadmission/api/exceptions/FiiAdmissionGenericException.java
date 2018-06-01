package com.fiiadmission.api.exceptions;

public class FiiAdmissionGenericException extends Exception{

    public FiiAdmissionGenericException(String message) {
        super(message);
    }

    public FiiAdmissionGenericException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
