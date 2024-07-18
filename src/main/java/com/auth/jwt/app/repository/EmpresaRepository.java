package com.auth.jwt.app.repository;

import com.auth.jwt.app.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {
    List<Empresa> findByActiveTrue();
    Optional<Empresa> findByAccountIdAndActiveTrue(String accountId);
}