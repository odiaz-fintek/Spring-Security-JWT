package com.auth.jwt.app.controller;

import com.auth.jwt.app.entity.Role;
import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.payload.AutenticacionLogin;
import com.auth.jwt.app.payload.AutenticacionResponse;
import com.auth.jwt.app.payload.DTOUsuario;
import com.auth.jwt.app.security.service.MiUserDetailsService;
import com.auth.jwt.app.security.utils.JwtUtil;
import com.auth.jwt.app.service.IRoleService;
import com.auth.jwt.app.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

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

    @Value("${token.palabra.secreta}")
    private String SECRETO;

    /* ~ Rutas publicas
    ------------------------------------------------------------------------------- */
    @GetMapping("/public")
    public String homePublic(){
        logger.info("Accessed public home page");
        return "Pagina de inicio al publico";
    } // fin de la peticion

    @PostMapping("/registrarse")
    public ResponseEntity<?> registrarse(@RequestBody DTOUsuario usuarioDTO){
        logger.info("Request received to register a new user");
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(usuarioDTO.getUsername());
        nuevoUsuario.setCorreo(usuarioDTO.getCorreo());
        nuevoUsuario.setPassword(usuarioDTO.getPassword());
        try {
            nuevoUsuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
            // Generar apikey
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String apikey = encoder.encode(usuarioDTO.getUsername() + usuarioDTO.getPassword() + SECRETO);
            nuevoUsuario.setApikey(apikey);

            // Asignar role de user
            Role role = roleService.buscarRolePorId(3);
            nuevoUsuario.agregarRoleALista(role);
            nuevoUsuario.setActivo(true);
            usuarioService.guardarUsuario(nuevoUsuario);
            logger.info("User registered successfully");
            // return ResponseEntity.ok(new AutenticacionResponse("apikey: " + apikey));
            return ResponseEntity.ok("Usuario registrado exitosamente");
        } catch (Exception e) {
            logger.error("Error registering user: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error registrando usuario");
        }
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin autLogin) throws Exception{
        logger.info("Request received to login");

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

            logger.info("User logged in successfully");

            HttpHeaders headers = new HttpHeaders();
            String authHeader = "Basic " + Base64.getEncoder().encodeToString((autLogin.getUsername() + ":" + autLogin.getPassword()).getBytes());
            headers.add("Authorization", authHeader);

        } catch (BadCredentialsException ex) {
            logger.error("Error logging in: {}", ex.getMessage(), ex);
            return ResponseEntity.status(401).body("Error en el username o contraseña: " + ex.getMessage());
        } catch (LockedException ex) {
            logger.error("Account is locked: {}", ex.getMessage(), ex);
            return ResponseEntity.status(403).body("Su cuenta está bloqueada. Por favor, intente más tarde: " + ex.getMessage());
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage(), ex);
            return ResponseEntity.status(500).body("Error en el usuario o contraseña: " + ex.getMessage());
        }// fin de try~catch

        // Obtenemos los datos del usuario de la BD para construir el token
        final Usuario usuario = usuarioService.buscarApikeyPorUsuario(autLogin.getUsername());
            if (usuario == null) {
                return ResponseEntity.status(404).body("Usuario no encontrado");
        }
        final String apikey = usuario.getApikey();
        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getUsername());
        final String token = jwtUtil.creatToken(userDetails);

        // Regresamos el token
        return ResponseEntity.ok(new AutenticacionResponse("Token: " + token +
        "                   Apikey: " + apikey + 
        "                   Basic: Acceso concedido"));
    } // fin para iniciar sesion

    // @PostMapping("/keep-alive")
    // public ResponseEntity<?> keepAlive(HttpServletRequest request) {
    //     HttpSession session = request.getSession(false);
    //     if (session != null) {
    //         session.setMaxInactiveInterval(300); // Reset to 5 minutes (300 seconds)
    //         return ResponseEntity.ok().build();
    //     } else {
    //         return ResponseEntity.notFound().build(); // Handle session not found
    //     }
    // }


    /* ~ Rutas privadas (requieren token)
    ------------------------------------------------------------------------------- */
    /*@GetMapping("/home")
    public String userAuthenticated(){
        logger.info("Acceso a Home por usuario autenticado");
        return "Welcome";
    }*/

    @GetMapping("/logoutforced")
    public String logoutForced() {
        logger.info("Logout de usuario");
        return "Su sesion a terminado por inactividad";
    }

} // fin del controlador home
