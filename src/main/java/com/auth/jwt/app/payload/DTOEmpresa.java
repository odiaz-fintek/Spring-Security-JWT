package com.auth.jwt.app.payload;


import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a Data Transfer Object (DTO) for an Empresa (Company).
 * It contains the properties related to an Empresa entity.
 */
@Getter @Setter
public class DTOEmpresa {

    private String accountId;
    private String type;
    private String account;
    private String mainPhone;
    private String email;
    private String secCodeId;

}
