package com.codebaobao.exception;

public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 0;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(final String message) {
        super(message);
    }

    public UserNotFoundException(final Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
