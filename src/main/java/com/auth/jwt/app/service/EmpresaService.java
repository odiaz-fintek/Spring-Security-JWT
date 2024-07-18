package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Empresa;
import com.auth.jwt.app.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class EmpresaService implements IEmpresaService {

    private final EmpresaRepository empresaRepository;
    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findByActiveTrue();
    }

    @Override
    public Optional<Empresa> findById(String accountId) {
        return empresaRepository.findByAccountIdAndActiveTrue(accountId);
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public void deleteById(String accountId) {
        Optional<Empresa> accountOptional = empresaRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Empresa empresa = accountOptional.get();
            empresa.setActive(false); // Change this line to set active to false
            empresaRepository.save(empresa);
        }
    }
}