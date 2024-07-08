package com.auth.jwt.app.repository;


import com.auth.jwt.app.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Este repositorio extiende de {@link JpaRepository} que permite usar los metodos
 * para las operaciones basicas de un CRUD que se haran hacia la tabla de <b>roles</b>.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

} // fin del repositorio
