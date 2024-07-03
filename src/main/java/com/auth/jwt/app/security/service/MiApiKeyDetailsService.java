package com.auth.jwt.app.security.service;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.auth.jwt.app.entity.Usuario;
// import com.auth.jwt.app.repository.UsuarioRepository;

// import java.util.Optional;

// public class MiApiKeyDetailsService implements UserDetailsService {
//     @Autowired
//     private UsuarioRepository usuarioRepository;

//     @Override
//     public UserDetails loadUserByUsername(String apikey) throws UsernameNotFoundException {
//         Optional<Usuario> usuario = usuarioRepository.buscarUsuarioPorApiKey(apikey);
//         usuario.orElseThrow(() -> new UsernameNotFoundException("No se encontro la apikey "+ apikey
//                 +" en la BD"));

//         return usuario.map(MiUserDetails::new).get();

//     } // din de la carga
// }
