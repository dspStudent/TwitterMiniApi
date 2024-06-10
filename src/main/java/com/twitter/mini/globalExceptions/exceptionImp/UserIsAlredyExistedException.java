package com.twitter.mini.globalExceptions.exceptionImp;

public class UserIsAlredyExistedException extends Exception{
    public UserIsAlredyExistedException() {
        super();
    }

    public UserIsAlredyExistedException(String message) {
        super(message);
    }

    public UserIsAlredyExistedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserIsAlredyExistedException(Throwable cause) {
        super(cause);
    }

    protected UserIsAlredyExistedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
