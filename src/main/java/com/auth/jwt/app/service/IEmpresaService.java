package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Empresa;

import java.util.*;

/**
 * This interface represents the service layer for managing Empresa entities.
 */
public interface IEmpresaService {

    /**
     * Retrieves all Empresa entities.
     *
     * @return a list of all Empresa entities
     */
    List<Empresa> findAll();

    /**
     * Retrieves an Empresa entity by its account ID.
     *
     * @param accountId the account ID of the Empresa entity to retrieve
     * @return an Optional containing the Empresa entity, or an empty Optional if not found
     */
    Optional<Empresa> findById(String accountId);

    /**
     * Saves an Empresa entity.
     *
     * @param empresa the Empresa entity to save
     * @return the saved Empresa entity
     */
    Empresa save(Empresa empresa);

    /**
     * Deletes an Empresa entity by its account ID.
     *
     * @param accountId the account ID of the Empresa entity to delete
     */
    void deleteById(String accountId);
}
