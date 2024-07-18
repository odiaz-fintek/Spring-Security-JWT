package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Este repositorio extiende de {@link JpaRepository} que permite usar los metodos
 * para las operaciones basicas de un CRUD que se haran hacia la tabla de <b>usuarios</b>.
 */
@Repository
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

    @Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Optional<Usuario> buscarApikeyPorUsername(String username);

    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.apikey = ?1")
    boolean buscarApikeyPorApikey(String apikey);

    @Query("SELECT u.apikeyActivo FROM Usuario u WHERE u.apikey = ?1")
    boolean buscarApikeyActivoPorApikey(String apikey);

} // fin de la clase
