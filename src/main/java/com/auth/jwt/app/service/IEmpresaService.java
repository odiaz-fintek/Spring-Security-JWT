package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Empresa;

import java.util.*;

public interface IEmpresaService {

    List<Empresa> findAll();

    Optional<Empresa> findById(String accountId);

    Empresa save(Empresa empresa);

    void deleteById(String accountId);
}
