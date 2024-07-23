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

    /**
     * Generates a custom query to retrieve a Usuario record by its username.
     * 
     * @param username the username of the Usuario
     * @return an Optional containing the Usuario record, or null if not found
     */
    @Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Optional<Usuario> buscarApikeyPorUsername(String username);

    /**
     * Generates a custom query to check if a Usuario record with the given apikey exists.
     * 
     * @param apikey the apikey to search for
     * @return true if a Usuario record with the given apikey exists, false otherwise
     */
    @Query("SELECT COUNT(u) > 0 FROM Usuario u WHERE u.apikey = ?1")
    boolean buscarApikeyPorApikey(String apikey);

    /**
     * Generates a custom query to retrieve a Usuario record by its apikey.
     * 
     * @param apikey the apikey of the Usuario
     * @return an Optional containing the Usuario record, or null if not found
     */
    @Query("SELECT u FROM Usuario u WHERE u.apikey = ?1")
    Optional<Usuario> findByApikey(String apikey);

    /**
     * Generates a custom query to check if the apikey of a Usuario record is active.
     * 
     * @param apikey the apikey to search for
     * @return true if the apikey of the Usuario record is active, false otherwise
     */
    @Query("SELECT u.apikeyActivo FROM Usuario u WHERE u.apikey = ?1")
    boolean buscarApikeyActivoPorApikey(String apikey);

} // fin de la clase
