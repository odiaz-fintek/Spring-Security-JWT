package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Usuario;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;


@Component
public class AuthBlock implements AuthenticationProvider
{

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UserDetailsService userDetailsService;


    private static final int MAX_FAILED_ATTEMPTS = UsuarioService.MAX_FAILED_ATTEMPTS;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        Usuario usuario = usuarioService.buscarUsuarioPorUsername(username);

        // check if the user exist
        if (usuario == null) {
            throw new BadCredentialsException("Usuario no encontrado");
        }

        // tells if the acc is blocked
        if (!usuario.isAccountNonLocked()) {
            if (usuarioService.unlockWhenTimeExpired(usuario)) {
                throw new LockedException("Su cuenta ha sido desbloqueada. Por favor, intente de nuevo.");
            }
            throw new LockedException("Su cuenta está bloqueada. Por favor, intente más tarde.");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (userDetails != null && new BCryptPasswordEncoder().matches(password, userDetails.getPassword())) {
            // Reset failed attempts upon successful login
            usuarioService.resetFailedAttempts(username);
            return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
        } else {
            // Increase failed attempts
            usuarioService.increaseFailedAttempts(usuario);
            // Check if account should be locked
            if (usuario.getFailedAttempt() >= MAX_FAILED_ATTEMPTS) {
                usuario.setAccountNonLocked(false);
                usuarioService.lock(usuario);
                throw new LockedException("Su cuenta ha sido bloqueada debido a múltiples intentos fallidos.");
            }

            throw new BadCredentialsException("Credenciales incorrectas.");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

