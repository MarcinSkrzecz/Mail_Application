package com.marcin.mail_application.domain.exceptions;

public class ValidationInputException extends Exception {
    public ValidationInputException(final String message) {
        super(message);
    }
}