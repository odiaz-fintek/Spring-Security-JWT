package com.auth.jwt.app.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents an error response containing a message.
 */
@Setter
@Getter
public class ErrorResponse {
    private String message;

    /**
     * Constructs a new ErrorResponse object with the specified message.
     *
     * @param message the error message
     */
    public ErrorResponse(String message) {
        this.message = message;
    }
}
