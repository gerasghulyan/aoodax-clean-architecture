package com.aoodax.platform.contract.input.exception;

public class EntityAlreadyExistsException extends AoodaxException {
    public EntityAlreadyExistsException() {
        this("Entity already exists");
    }

    public EntityAlreadyExistsException(final String message) {
        super(message, null, false, false);
    }

    public EntityAlreadyExistsException(final Throwable cause) {
        super(cause);
    }

    public EntityAlreadyExistsException(final String message,
                                               final Throwable cause) {
        super(message, cause);
    }
}
