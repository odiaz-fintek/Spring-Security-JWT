package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * This interface represents the repository for the Empresa entity.
 * It extends the JpaRepository interface, providing CRUD operations for the Empresa entity.
 */
public interface EmpresaRepository extends JpaRepository<Empresa, String> {

    /**
     * Retrieves a list of active empresas.
     *
     * @return A list of active empresas.
     */
    List<Empresa> findByActiveTrue();

    /**
     * Retrieves an optional Empresa object based on the provided accountId.
     *
     * @param accountId The accountId to search for.
     * @return An optional Empresa object if found, otherwise empty.
     */
    Optional<Empresa> findByAccountIdAndActiveTrue(String accountId);
}