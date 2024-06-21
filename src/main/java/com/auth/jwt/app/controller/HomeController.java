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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

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
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin autLogin) throws Exception {

        logger.info("Request received to login");

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

            logger.info("User logged in successfully");
            return ResponseEntity.ok("Inicio de sesión exitoso");
            // Obtenemos los datos del usuario de la BD para construir el token


            // Regresamos el token
        } catch (BadCredentialsException ex) {
            logger.error("Error logging in: {}", ex.getMessage(), ex);
            throw new Exception("Error en el username o contraseña " + ex.getMessage());
        }
    }

    @PostMapping("/keep-alive")
    public ResponseEntity<?> keepAlive(HttpServletRequest request) {
        /*final String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null && headerAuth.startsWith("Bearer ")) {
            String token = headerAuth.substring(7);
            String username = jwtUtil.extraerUsername(token);
            UserDetails userDetails = miUserDetailsService.loadUserByUsername(username);

            if (jwtUtil.validarToken(token, userDetails)) {
                String newToken = jwtUtil.creatToken(userDetails);
                return ResponseEntity.ok(new AutenticacionResponse(newToken));
            }
        }
        return ResponseEntity.status(HttpSta*/tus.UNAUTHORIZED).body("Invalid token");
    }

    /* ~ Rutas privadas (requieren token)
    ------------------------------------------------------------------------------- */
    @GetMapping("/home")
    public String userAuthenticated() {
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

} // fin del controlador home
