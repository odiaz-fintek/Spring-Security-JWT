package com.auth.jwt.app.payload;


import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for a user.
 * It contains the user's username, email, and password.
 */
@Getter @Setter
public class DTOUsuario {
    private String username;
    private String correo;
    private String password;
}
