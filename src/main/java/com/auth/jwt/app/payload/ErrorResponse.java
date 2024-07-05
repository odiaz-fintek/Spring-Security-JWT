package com.auth.jwt.app.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    // Getter y Setter
    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }

}
