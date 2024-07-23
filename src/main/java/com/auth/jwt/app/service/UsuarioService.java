package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Usuario;
import com.auth.jwt.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
/**
 * Clase que implementa los metodos de la interfaz {@link IUsuarioService} del servicio para
 * usuarios.
 */
/**
 * This class represents the service layer for managing users.
 * It implements the {@link IUsuarioService} interface.
 * 
 * <p>
 * The service handles user-related operations such as retrieving users, saving users,
 * deleting users, and managing login attempts and account locking.
 * </p>
 * 
 * <p>
 * The service uses the {@link UsuarioRepository} to access the user data in the database.
 * </p>
 * 
 * @see IUsuarioService
 * @see UsuarioRepository
 */
@Service
public class UsuarioService implements IUsuarioService {

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
        usuario.setFailedAttempt(newFailAttempts);

        if (newFailAttempts >= MAX_FAILED_ATTEMPTS) {
            lock(usuario);
        } else {
            usuarioRepository.save(usuario);
        }
    }

    public void resetFailedAttempts(String username) {
        Usuario usuario = buscarUsuarioPorUsername(username);
        if (usuario != null) {
            usuario.setFailedAttempt(0);
            usuarioRepository.save(usuario);
        }
    }

    /**
     * Locks the specified user account by setting the accountNonLocked flag to false
     * and updating the lockTime to the current date and time.
     *
     * @param usuario the user account to be locked
     */
    public void lock(Usuario usuario) {
        usuario.setAccountNonLocked(false);
        usuario.setLockTime(new java.sql.Date(System.currentTimeMillis()));
        usuarioRepository.save(usuario);
    }

    /**
     * Unlocks the user account when the lock time has expired.
     * 
     * @param usuario the user for which the account needs to be unlocked
     * @return true if the account was successfully unlocked, false otherwise
     */
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

    /**
        * Retrieves the API key associated with the given username.
        *
        * @param username the username of the user
        * @return the API key associated with the username, or null if not found
        */
    @Override @Transactional(readOnly = true)
    public Usuario buscarApikeyPorUsuario(String username) {
        // Usuario api_key = usuarioRepository.buscarApikeyPorUsername(username).orElse(null);
        return usuarioRepository.buscarApikeyPorUsername(username).orElse(null);
    }

    /**
     * Searches for a user by their API key.
     *
     * @param apikey the API key to search for
     * @return true if a user with the given API key is found, false otherwise
     */
    @Override @Transactional(readOnly = true)
    public Boolean buscarApikeyPorApikey(String apikey) {
        return usuarioRepository.buscarApikeyPorApikey(apikey);
    }

    /**
     * Searches for the state of an API key.
     *
     * @param apikey the API key to search for
     * @return true if the API key is active, false otherwise
     */
    @Override @Transactional(readOnly = true)
    public Boolean buscarEstadoApikey(String apikey) {
        return usuarioRepository.buscarApikeyActivoPorApikey(apikey);
    }

    /**
     * Checks if the provided API key has expired.
     *
     * @param apikey The API key to check for expiration.
     * @return true if the API key has expired, false otherwise.
     */
    @Override
    public boolean hasApikeyExpired(String apikey) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByApikey(apikey);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return usuario.getApikeySesionTime().plusMinutes(5).isBefore(LocalDateTime.now());
        }
        return true; // Si el usuario no se encuentra, tratamos el API key como expirado
    }

    /**
     * Updates the session time of the user's API key.
     * If the user with the given API key exists, their API key session time is updated to the current time.
     *
     * @param apikey the API key of the user
     */
    @Override
    public void actualizarApikeySesionTime(String apikey) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByApikey(apikey);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setApikeySesionTime(LocalDateTime.now());
            usuarioRepository.save(usuario);
        }
    }


} // fin de la implementacion de los servicios
