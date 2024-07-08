package com.auth.jwt.app.payload;

import lombok.Getter;
import lombok.Setter;

/*
* Este DTO es utilizado para "eliminar" un cliente,
* cambiando su estado a inactivo.
* */

@Getter @Setter
public class DTODeleteCliente {
    private Long clienteid;
//    private Boolean status = false; // Cambiar el estado a false

    // Getters y setters
}
