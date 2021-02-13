package com.marcin.mail_application.domain.exceptions;

public class WrongMagicNumberException extends Exception {
    public WrongMagicNumberException(final String message) {
        super(message);
    }
}