package com.twitter.mini.globalExceptions.exceptionImp;

public class UserTokenExpiredException extends Exception{
    public UserTokenExpiredException() {
        super();
    }

    public UserTokenExpiredException(String message) {
        super(message);
    }

    public UserTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserTokenExpiredException(Throwable cause) {
        super(cause);
    }

    protected UserTokenExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
