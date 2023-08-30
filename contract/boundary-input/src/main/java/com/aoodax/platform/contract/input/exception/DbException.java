package com.aoodax.platform.contract.input.exception;

public class DbException extends AoodaxException {
    public DbException() {
        this("Unexpected db exception");
    }

    public DbException(final String message) {
        super(message, null, false, false);
    }

    public DbException(final Throwable cause) {
        super(cause);
    }

    public DbException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }
}
