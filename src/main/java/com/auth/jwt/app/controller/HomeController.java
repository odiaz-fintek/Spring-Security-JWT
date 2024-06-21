package com.auth.jwt.app.controller;

import com.auth.jwt.app.entity.Role;
import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.payload.AutenticacionLogin;
import com.auth.jwt.app.payload.AutenticacionResponse;
import com.auth.jwt.app.security.service.MiUserDetailsService;
import com.auth.jwt.app.security.utils.JwtUtil;
import com.auth.jwt.app.service.IRoleService;
import com.auth.jwt.app.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok("Usuario registrado correctamente");
    } // fin de la pagina de registro

    @PostMapping("/iniciar")
    public ResponseEntity<?> iniciarSesion(@RequestBody AutenticacionLogin autLogin) throws Exception{
        //autLogin.getPassword();
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(autLogin.getUsername(), autLogin.getPassword())
            );
            return ResponseEntity.ok("Inicio de sesión exitoso");

        }catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error en el username o contraseña: " + ex.getMessage());
        } // fin de try~catch

//        // Obtenemos los datos del usuario de la BD para construir el token
//        final UserDetails userDetails = miUserDetailsService.loadUserByUsername(autLogin.getUsername());
//        final String token = jwtUtil.creatToken(userDetails);
//
//        // Regresamos el token
//        return ResponseEntity.ok(new AutenticacionResponse(token));
//        return ResponseEntity.ok("Inicio de sesión exitoso");
    } // fin para iniciar sesion


    /* ~ Rutas privadas (requieren token)
    ------------------------------------------------------------------------------- */
    @GetMapping("/home")
    public String userAuthenticated(){
        return "Welcome";
    }

} // fin del controlador home
