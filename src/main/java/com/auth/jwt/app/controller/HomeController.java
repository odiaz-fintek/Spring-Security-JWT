package com.auth.jwt.app.controller;

import com.auth.jwt.app.entity.Role;
import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.payload.AutenticacionLogin;
import com.auth.jwt.app.payload.AutenticacionRegistro;
import com.auth.jwt.app.payload.AutenticacionResponse;
import com.auth.jwt.app.security.service.MiUserDetails;
import com.auth.jwt.app.security.service.MiUserDetailsService;
import com.auth.jwt.app.security.utils.JwtUtil;
import com.auth.jwt.app.service.IRoleService;
import com.auth.jwt.app.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.auth.jwt.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    /* ~ Autowired
    ------------------------------------------------------------------------------- */
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private MiUserDetailsService miUserDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService customUsuarioService;

    /* ~ Rutas publicas
    ------------------------------------------------------------------------------- */
    @GetMapping("/public")
    public String homePublic() {
        logger.info("Accessed public home page");
        return "Pagina de inicio al publico";
    }

    @PostMapping("/registrarse")
    public ResponseEntity<?> registrarse(@RequestBody Usuario usuario) {
        logger.info("Request received to register a new user");

        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            // Asignar role de user
            Role role = roleService.buscarRolePorId(3);
            usuario.agregarRoleALista(role);
            usuario.setActivo(true);
            usuarioService.guardarUsuario(usuario);

            logger.info("User registered successfully");
            return ResponseEntity.ok("Usuario registrado correctamente");
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error registrando usuario");
        }
    }

    @PostMapping("/iniciar")
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

    @PostMapping("/keep-alive")
    public ResponseEntity<?> keepAlive(HttpServletRequest request) {
        final String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.substring(7);
            String username = jwtUtil.extraerUsername(token);
            UserDetails userDetails = miUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validarToken(token, userDetails)) {
                String newToken = jwtUtil.creatToken(userDetails);
                return ResponseEntity.ok(new AutenticacionResponse(newToken));
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
    }

    /* ~ Rutas privadas (requieren token)
    ------------------------------------------------------------------------------- */
    @GetMapping("/home")
    public String userAuthenticated() {
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

} // fin del controlador home
