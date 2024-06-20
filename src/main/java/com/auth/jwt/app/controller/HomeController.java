package com.auth.jwt.app.controller;

import com.auth.jwt.app.entity.Role;
import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.payload.AutenticacionLogin;
import com.auth.jwt.app.payload.AutenticacionResponse;
import com.auth.jwt.app.security.service.MiUserDetailsService;
import com.auth.jwt.app.security.utils.JwtUtil;
import com.auth.jwt.app.service.IRoleService;
import com.auth.jwt.app.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

            // Obtenemos los datos del usuario de la BD para construir el token
            final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getUsername());
            final String token = jwtUtil.creatToken(userDetails);

            logger.info("User logged in successfully");
            // Regresamos el token
            return ResponseEntity.ok(new AutenticacionResponse(token));
        } catch (BadCredentialsException ex) {
            logger.error("Error logging in: {}", ex.getMessage(), ex);
            throw new Exception("Error en el username o contraseña " + ex.getMessage());
        }
    }

    @GetMapping("/home")
    public String userAuthenticated() {
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }

} // fin del controlador home
