package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Clase que implementa los metodos de la interfaz {@link IUsuarioService} del servicio para
 * usuarios.
 */
@Service
@Transactional
public class UsuarioService implements IUsuarioService {
    /* ~ Constantes
    ==================================== */

    /*
        maneja los intentos de inicio de sesión y el bloqueo de cuenta.
    */
    public static final int MAX_FAILED_ATTEMPTS = 3;
//    private static final long LOCK_TIME_DURATION = 24 * 60 * 60 * 1000; // 24 horas
    private static final long LOCK_TIME_DURATION = 3 * 60 * 1000; // 3 minutos en milisegundos

    /**
     * Inyeccion para acceder a los metodos del repositorio
     */
    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarTodosUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorId(Integer idUsuario) {
        return usuarioRepository.findById(idUsuario).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorUsername(String username) {
        return usuarioRepository.buscarUsuarioPorUsername(username).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioRepository.buscarUsuarioPorCorreo(correo).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = false)
    public void eliminarUsuarioPorId(Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    /* ~ Manejo de intentos
    ==================================== */

    /*
        Servicios que maneja la cantidad de intentos
        bloquea la cuenta
        desbloquea la cuenta
    */
    public void increaseFailedAttempts(Usuario usuario) {
        int newFailAttempts = usuario.getFailedAttempt() + 1;
        usuarioRepository.updateFailedAttempts(newFailAttempts, usuario.getUsername());
    }

    public void resetFailedAttempts(String username) {
        usuarioRepository.updateFailedAttempts(0, username);
    }

    public void lock(Usuario usuario) {
        usuario.setAccountNonLocked(false);
        usuario.setLockTime((java.sql.Date) new Date());

        usuarioRepository.lockAccount(usuario.getLockTime(), usuario.getUsername());
    }

    public boolean unlockWhenTimeExpired(Usuario usuario) {
        long lockTimeInMillis = usuario.getLockTime().getTime();
        long currentTimeInMillis = System.currentTimeMillis();

        if (lockTimeInMillis + LOCK_TIME_DURATION < currentTimeInMillis) {
            usuario.setAccountNonLocked(true);
            usuario.setLockTime(null);
            usuario.setFailedAttempt(0);

            usuarioRepository.save(usuario);

            return true;
        }

        return false;
    }



} // fin de la implementacion de los servicios
