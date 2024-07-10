package com.auth.jwt.app.controller;

import com.auth.jwt.app.payload.AutenticacionLogin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/basic")
public class B_Controller {

    private static final Logger logger = LoggerFactory.getLogger(B_Controller.class);

    /* ~ Autowired
    ------------------------------------------------------------------------------- */
    @Autowired
    private AuthenticationManager authManager;

    /* ~ Rutas publicas
    ------------------------------------------------------------------------------- */
    @GetMapping("/b_public")
    public String homePublic() {
        logger.info("Accessed public home page");
        return "Pagina de inicio al publico";
    }

    @PostMapping("/b_iniciar")
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin autLogin) {

        logger.info("Request received to login");

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

            logger.info("User logged in successfully");
            return ResponseEntity.ok("Inicio de sesión exitoso");

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

    /* ~ Rutas privadas (requieren token)
    ------------------------------------------------------------------------------- */
    @GetMapping("/b_home")
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

    @GetMapping("/b_logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home