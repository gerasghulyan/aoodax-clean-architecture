package com.aoodax.platform.contract.input.exception;

public class EntityValidationException extends AoodaxException {
    public EntityValidationException() {
        this("Entity Validation Exception");
    }

    public EntityValidationException(final String message) {
        super(message, null, false, false);
    }

    public EntityValidationException(final Throwable cause) {
        super(cause);
    }

    public EntityValidationException(final String message,
                                   final Throwable cause) {
        super(message, cause);
    }
}
