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
@RequestMapping(value = "api/jwt")
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
    public String homePublic(){
        return "Pagina de inicio al publico";
    } // fin de la peticion

    @PostMapping("/registrarse")
    public ResponseEntity<?> registrarse(@RequestBody Usuario usuario){
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        // Asignar role de user
        Role role = roleService.buscarRolePorId(3);
        usuario.agregarRoleALista(role);
        usuario.setActivo(true);
        usuarioService.guardarUsuario(usuario);

        // Crea el token de este usuario
        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(usuario.getUsername());
        final String token = jwtUtil.creatToken(userDetails);

        // Guarda el token en el usuario
        usuario.setToken(token);
        usuarioService.guardarUsuario(usuario);

        return ResponseEntity.ok(new AutenticacionResponse(token));
    }

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin autLogin) throws Exception{
        //autLogin.getPassword();
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );

            final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getUsername());
            Usuario usuario = ((MiUserDetails) userDetails).getUsuario();

            /*Manejo de intentos*/
            if (!usuario.isAccountNonLocked()) {
                if (customUsuarioService.unlockWhenTimeExpired(usuario)) {
                    throw new Exception("Tu cuenta ha sido desbloqueada. Por favor, intenta de nuevo.");
                } else {
                    throw new Exception("Tu cuenta está bloqueada. Intenta después de 24 horas.");
                }
            }

            if (passwordEncoder.matches(autLogin.getPassword(), usuario.getPassword())) {
                if (usuario.getFailedAttempt() > 0) {
                    customUsuarioService.resetFailedAttempts(usuario.getUsername());
                }

                final String token = jwtUtil.creatToken(userDetails);
                return ResponseEntity.ok(new AutenticacionResponse(token));
            } else {
                if (usuario.getFailedAttempt() < UsuarioService.MAX_FAILED_ATTEMPTS - 1) {
                    customUsuarioService.increaseFailedAttempts(usuario);
                } else {
                    customUsuarioService.lock(usuario);
                    throw new Exception("Tu cuenta ha sido bloqueada debido a múltiples intentos fallidos.");
                }

                throw new Exception("Contraseña incorrecta.");
            }
        } catch (BadCredentialsException ex) {
            throw new Exception("Error en el username o contraseña " + ex.getMessage());
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
    public String userAuthenticated(){
        return "Welcome";
    }

} // fin del controlador home
