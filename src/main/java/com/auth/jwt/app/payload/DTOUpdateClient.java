package com.auth.jwt.app.payload;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/*
* Este DTO es para actualizar la información de un cliente existente.
* Tiene los mismos campos que el DTO de creación más un clienteId.
* */
@Getter @Setter
public class DTOUpdateClient {
//    private Long clienteid;
    private String firstname;
    private String middlename;
    private String lastname;
    private Date birthday;
    private Date createDate;
    private String email;
    private String mobile;
    private String homephone;
    private String nationality;
    private String secCodeId;

    // Getters y setters
}