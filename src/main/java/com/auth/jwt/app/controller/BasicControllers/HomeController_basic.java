package com.auth.jwt.app.controller.BasicControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwt.app.payload.AutenticacionLogin;

@RestController
@RequestMapping("/basic")
public class HomeController_basic {

    private static final Logger logger = LoggerFactory.getLogger(HomeController_basic.class);

    @Autowired
    private AuthenticationManager authManager;

    @GetMapping("/home")
    public ResponseEntity<?> userAuthenticated(@RequestBody AutenticacionLogin autLogin) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

            logger.info("Acceso a Home por usuario autenticado");
            return ResponseEntity.ok("Welcome to the home page!");

        } catch (BadCredentialsException ex) {
            logger.error("Error logging in: {}", ex.getMessage(), ex);
            return ResponseEntity.status(401).body("Error en el username o contraseña: " + ex.getMessage());
        } catch (LockedException ex) {
            logger.error("Account is locked: {}", ex.getMessage(), ex);
            return ResponseEntity.status(403).body("Su cuenta está bloqueada. Por favor, intente más tarde: " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("Error en el usuario o contraseña: " + ex.getMessage());
        }
    }
    // public String userAuthenticated(){
    //     logger.info("Acceso a Home por usuario autenticado");
    //     return "Welcome";
    // }

    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
