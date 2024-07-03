package com.auth.jwt.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @RequestMapping("/api")
public class ApiController {

    @GetMapping("/apikey")
    public String testEndpoint() {
        return "Access granted to secure endpoint!";
    }
}


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.web.bind.annotation.GetMapping;//
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;//
// import org.springframework.web.bind.annotation.RestController;//
// import org.springframework.security.core.userdetails.UserDetails;

// import com.auth.jwt.app.entity.Role;
// import com.auth.jwt.app.entity.Usuario;
// import com.auth.jwt.app.payload.AutenticacionApiKey;
// import com.auth.jwt.app.payload.AutenticacionResponse;
// import com.auth.jwt.app.security.service.MiUserDetailsService;
// import com.auth.jwt.app.security.utils.ApiKeyUtil;
// import com.auth.jwt.app.service.IRoleService;
// import com.auth.jwt.app.service.IUsuarioService;

// @RestController
// @RequestMapping("/api")
// public class ApiController {

//     @Autowired
//     private IRoleService roleService;
//     @Autowired
//     private IUsuarioService usuarioService;
//     @Autowired
//     private BCryptPasswordEncoder passwordEncoder;
//     @Autowired
//     private ApiKeyUtil ApiKeyUtil;
//     @Autowired
//     private MiUserDetailsService miUserDetailsService;
//     @Autowired
//     private AuthenticationManager authManager;
//     @Value("${token.palabra.secreta}")
//     private String SECRETO;
    
//     @GetMapping("/inicio")
//     public String homePublic(){
//         return "This is Spring Boot API secued data";
//     } // fin de la peticion

//     @PostMapping("/prueba")
//     public ResponseEntity<?> iniciarApiKey(@RequestBody AutenticacionApiKey usuario) throws Exception{
//         try {
//             authManager.authenticate(
//                     new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword())
//             );

//         }catch (BadCredentialsException ex){
//             throw new Exception("Error en el username o contraseña " + ex.getMessage());
//         } // fin de try~catch

//         final UserDetails userDetails = miUserDetailsService.loadUserByUsername(usuario.getUsername());
//         final String apiKey = ApiKeyUtil.createApiKey(userDetails);

//         return ResponseEntity.ok(new AutenticacionResponse(apiKey));
        // usuario.setApiKey(usuario.getApiKey());
        // usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        
        // Generar apikey
        // BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // usuario.setApikey(encoder.encode(usuario.getUsername() + usuario.getPassword() + SECRETO));

        // Asignar role de user
        // Role role = roleService.buscarRolePorId(3);
        // usuario.agregarRoleALista(role);
        // usuario.setActivo(true);
        // usuarioService.guardarUsuario(usuario);

        // return ResponseEntity.ok("Usuario registrado correctamente");
    // } // fin de la pagina de registro



// } // fin de la clase ApiController
