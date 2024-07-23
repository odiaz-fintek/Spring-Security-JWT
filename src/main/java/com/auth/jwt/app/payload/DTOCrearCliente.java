package com.auth.jwt.app.payload;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

/**
 * This class represents a data transfer object (DTO) for creating a client.
 * It contains the client's personal information such as name, birthday, contact details, and nationality.
 */
@Getter @Setter
public class DTOCrearCliente {
    private String firstname;
    private String middlename;
    private String lastname;
    private Date birthday;
//    private Date createDate = new Date(); // Fecha actual
    private String email;
    private String mobile;
    private String homephone;
    private String nationality;
//    private String secCodeId = UUID.randomUUID().toString(); // Valor random

    // Getters y setters
}
