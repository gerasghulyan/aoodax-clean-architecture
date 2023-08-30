package com.aoodax.platform.contract.input.exception;

public class NotFoundException extends AoodaxException {
    public NotFoundException() {
        this("Entity not found");
    }

    public NotFoundException(final String message) {
        super(message, null, false, false);
    }

    public NotFoundException(final Throwable cause) {
        super(cause);
    }

    public NotFoundException(final String message,
                             final Throwable cause) {
        super(message, cause);
    }
}
