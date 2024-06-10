package com.twitter.mini.globalExceptions.exceptionImp;

public class EmptyContentGetException extends Exception{
    public EmptyContentGetException() {
        super();
    }

    public EmptyContentGetException(String message) {
        super(message);
    }

    public EmptyContentGetException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyContentGetException(Throwable cause) {
        super(cause);
    }

    protected EmptyContentGetException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
