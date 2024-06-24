package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Usuario;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;


@Component
public class AuthBlock implements AuthenticationProvider {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.buscarUsuarioPorUsername(username);

        if (usuario == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        if (!usuario.isAccountNonLocked()) {
            if (usuarioService.unlockWhenTimeExpired(usuario)) {
                throw new LockedException("Su cuenta ha sido desbloqueada. Por favor, intente de nuevo.");
            }
            throw new LockedException("Su cuenta está bloqueada. Por favor, intente más tarde.");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails.getPassword().equals(password)) {
            usuarioService.resetFailedAttempts(username);
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            usuarioService.increaseFailedAttempts(usuario);
            throw new BadCredentialsException("Credenciales incorrectas.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}

