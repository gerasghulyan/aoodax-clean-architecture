package com.aoodax.platform.contract.input.exception;

public class EntityNotFoundException extends AoodaxException {
    public EntityNotFoundException() {
        this("Entity not found");
    }

    public EntityNotFoundException(final String message) {
        super(message, null, false, false);
    }

    public EntityNotFoundException(final Throwable cause) {
        super(cause);
    }

    public EntityNotFoundException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }
}
