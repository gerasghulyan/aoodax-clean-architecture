package com.aoodax.platform.contract.input.exception;

public class AoodaxException extends RuntimeException {
    public AoodaxException() {
        super();
    }

    public AoodaxException(final String message) {
        super(message);
    }

    public AoodaxException(final Throwable cause) {
        super(cause);
    }

    public AoodaxException(final String message,
                           final Throwable cause,
                           final boolean enableSuppression,
                           final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AoodaxException(final String message,
                           final Throwable cause) {
        super(message, cause);
    }
}
