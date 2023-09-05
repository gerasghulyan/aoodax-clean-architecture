package com.aoodax.platform.contract.input.exception;

public class AlreadyExistsException extends AoodaxException {
    public AlreadyExistsException() {
        this("Already exists");
    }

    public AlreadyExistsException(final String message) {
        super(message, null, false, false);
    }

    public AlreadyExistsException(final Throwable cause) {
        super(cause);
    }

    public AlreadyExistsException(final String message,
                                  final Throwable cause) {
        super(message, cause);
    }
}
