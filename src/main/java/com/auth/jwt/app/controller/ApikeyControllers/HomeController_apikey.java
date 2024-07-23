package com.auth.jwt.app.controller.ApikeyControllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

/**
 * Esta clase representa el controlador para la autenticación APIKEY.
 * Maneja los endpoints relacionados con la autenticación APIKEY.
 */
@RestController
@Tag(name = "ApiKey", description = "Controller with APIKEY Authentication")
@RequestMapping("/apikey")
@SecurityRequirement(name = "apiKeyAuth")
public class HomeController_apikey {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_apikey.class);

    /**
     * Este endpoint se utiliza para acceder a la página de inicio para usuarios autenticados.
     *
     * @return The welcome message.
     */
    @GetMapping("/home")
    public String userAuthenticated(){
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

    /**
     * Este endpoint es de prueba
     *
     * @return The access granted message.
     */
    @GetMapping("/check")
    public String testEndpoint() {
        return "Access granted to secure endpoint!";
    }

    /**
     * Este endpoint se utiliza para forzar el cierre de sesión de un usuario.
     * Cierra la sesión del usuario y devuelve un mensaje indicando que la sesión ha finalizado por inactividad.
     *
     * @return The logout message.
     */
    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
