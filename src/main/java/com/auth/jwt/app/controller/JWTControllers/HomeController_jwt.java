package com.auth.jwt.app.controller.JWTControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@Tag(name = "JWT", description = "Controller with JWT Authentication")
@RequestMapping("/jwt")
@SecurityRequirement(name = "bearerAuth")
public class HomeController_jwt {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_jwt.class);

    @Value("${token.palabra.secreta}")
    private String SECRETO;

    @GetMapping("/home")
    public String userAuthenticated(){
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
