package com.twitter.mini.globalExceptions.exceptionImp;

public class UserNotVerfiedException extends Exception{
    public UserNotVerfiedException() {
        super();
    }

    public UserNotVerfiedException(String message) {
        super(message);
    }

    public UserNotVerfiedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotVerfiedException(Throwable cause) {
        super(cause);
    }

    protected UserNotVerfiedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
