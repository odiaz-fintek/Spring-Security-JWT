package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
/**
 * This interface represents the repository for managing the Cliente entities.
 * It extends the JpaRepository interface, providing CRUD operations for the Cliente entity.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    /**
     * Retrieves a list of Cliente entities by their first name.
     *
     * @param firstname the first name of the Cliente entities to retrieve
     * @return a list of Cliente entities with the specified first name
     */
    List<Cliente> findByfirstname(String firstname);

    /**
     * Retrieves a list of Cliente entities by their status.
     *
     * @param status the status of the Cliente entities to retrieve
     * @return a list of Cliente entities with the specified status
     */
    List<Cliente> findBystatus(String status);

    /**
     * Retrieves a list of Cliente entities by their location code.
     *
     * @param locationCode the location code of the Cliente entities to retrieve
     * @return a list of Cliente entities with the specified location code
     */
    List<Cliente> findBylocationCode(String locationCode);

    /**
     * Retrieves a list of Cliente entities by their create date.
     *
     * @param createDate the create date of the Cliente entities to retrieve
     * @return a list of Cliente entities with the specified create date
     */
    List<Cliente> findBycreateDate(Date createDate);
}
