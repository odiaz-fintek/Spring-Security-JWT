package com.auth.jwt.app.security.service;

import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean authenticate(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null && usuario.getPassword().equals(password)) {
            // Reset failed attempts on successful login
            usuario.resetFailedLoginAttempts();
            usuarioRepository.save(usuario);
            return true;
        } else {
            incrementFailedAttempts(username);
            return false;
        }
    }

    public void incrementFailedAttempts(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario != null) {
            usuario.incrementFailedLoginAttempts();
            usuarioRepository.save(usuario);
            if (usuario.getFailedLoginAttempts() >= 3) {
                // Implement logic to block the user, e.g., set a flag or status
                usuario.setActivo(false); // Example: deactivate user
                usuarioRepository.save(usuario);
            }
        }
    }

    // Additional methods as needed
}