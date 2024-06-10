package com.twitter.mini.globalExceptions.exceptionImp;

public class UserTokenIsInvalidException extends Exception{
    public UserTokenIsInvalidException() {
        super();
    }

    public UserTokenIsInvalidException(String message) {
        super(message);
    }

    public UserTokenIsInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTokenIsInvalidException(Throwable cause) {
        super(cause);
    }

    protected UserTokenIsInvalidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
