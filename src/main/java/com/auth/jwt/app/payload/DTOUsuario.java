package com.auth.jwt.app.payload;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOUsuario {
    private String username;
    private String correo;
    private String password;
}
