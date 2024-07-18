package com.auth.jwt.app.payload;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DTOEmpresa {

    private String accountId;
    private String type;
    private String account;
    private String mainPhone;
    private String email;

}
