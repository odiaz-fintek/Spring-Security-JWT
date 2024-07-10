package com.auth.jwt.app.controller.ApikeyControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apikey")
public class HomeController_apikey {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_apikey.class);

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
