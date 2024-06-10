package com.twitter.mini.globalExceptions.exceptionImp;

public class UserCredentailsAreWrong extends Exception{
    public UserCredentailsAreWrong() {
        super();
    }

    public UserCredentailsAreWrong(String message) {
        super(message);
    }

    public UserCredentailsAreWrong(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCredentailsAreWrong(Throwable cause) {
        super(cause);
    }

    protected UserCredentailsAreWrong(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
