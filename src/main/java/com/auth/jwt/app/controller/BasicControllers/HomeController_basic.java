package com.auth.jwt.app.controller.BasicControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;



@RestController
@Tag(name = "Basic", description = "Controller with Basic Authentication")
@RequestMapping("/basic")
@SecurityRequirement(name = "basicAuth")
public class HomeController_basic {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_basic.class);

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/home")
    public ResponseEntity<?> userAuthenticated() {
        logger.info("Acceso a Home por usuario autenticado");
        return ResponseEntity.ok("Welcome to the home page!");
    }

    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
