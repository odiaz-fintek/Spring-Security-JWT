package com.auth.jwt.app.controller.ApikeyControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@Tag(name = "ApiKey", description = "Controller with APIKEY Authentication")
@RequestMapping("/apikey")
@SecurityRequirement(name = "apiKeyAuth")
public class HomeController_apikey {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_apikey.class);

    @GetMapping("/home")
    public String userAuthenticated(){
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

    @GetMapping("/check")
    public String testEndpoint() {
        return "Access granted to secure endpoint!";
    }

    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
