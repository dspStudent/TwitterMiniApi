package com.twitter.mini.globalExceptions.exceptionImp;

public class UserVerficationPinExpiredException extends Exception{
    public UserVerficationPinExpiredException() {
        super();
    }

    public UserVerficationPinExpiredException(String message) {
        super(message);
    }

    public UserVerficationPinExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserVerficationPinExpiredException(Throwable cause) {
        super(cause);
    }

    protected UserVerficationPinExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
