package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

/**
 * Este repositorio extiende de {@link JpaRepository} que permite usar los metodos
 * para las operaciones basicas de un CRUD que se haran hacia la tabla de <b>usuarios</b>.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Genera una consulta personalizada que permita obtener el registro de un
     * <b>usuario</b> atraves de su username.
     * @param username
     * @return Optional (usuario) o null
     */
    @Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Optional<Usuario> buscarUsuarioPorUsername(String username);

    /**
     * Genera una consulta personalizada que permita obtener el registro de un
     * <b>usuario</b> atraves de su correo.
     * @param correo
     * @return Optional
     */
    @Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
    Optional<Usuario> buscarUsuarioPorCorreo(String correo);

    // Actualizar intentos fallidos
    @Modifying
    @Query("UPDATE Usuario u SET u.failedAttempt = ?1 WHERE u.username = ?2")
    void updateFailedAttempts(int failedAttempts, String username);

    // Bloquear cuenta
    @Modifying
    @Query("UPDATE Usuario u SET u.accountNonLocked = false, u.lockTime = ?1 WHERE u.username = ?2")
    void lockAccount(Date lockTime, String username);
} // fin de la clase
