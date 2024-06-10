package com.twitter.mini.globalExceptions.exceptionImp;

public class PasswordAndConfrimPasswordsAreWrongException extends Exception{
    public PasswordAndConfrimPasswordsAreWrongException() {
        super();
    }

    public PasswordAndConfrimPasswordsAreWrongException(String message) {
        super(message);
    }

    public PasswordAndConfrimPasswordsAreWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordAndConfrimPasswordsAreWrongException(Throwable cause) {
        super(cause);
    }

    protected PasswordAndConfrimPasswordsAreWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
