package com.twitter.mini.globalExceptions.exceptionImp;

public class UserVerificationPinIsWrongException extends Exception{
    public UserVerificationPinIsWrongException() {
        super();
    }

    public UserVerificationPinIsWrongException(String message) {
        super(message);
    }

    public UserVerificationPinIsWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserVerificationPinIsWrongException(Throwable cause) {
        super(cause);
    }

    protected UserVerificationPinIsWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
