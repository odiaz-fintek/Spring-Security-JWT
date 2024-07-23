package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Empresa;
import com.auth.jwt.app.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * This class represents the service layer for Empresa entities.
 * It provides methods to perform CRUD operations on Empresa objects.
 */
@Transactional
@Service
public class EmpresaService implements IEmpresaService {

    private final EmpresaRepository empresaRepository;
    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    /**
     * Retrieves all active empresas from the repository.
     *
     * @return a list of Empresa objects representing all active empresas.
     */
    @Override
    public List<Empresa> findAll() {
        return empresaRepository.findByActiveTrue();
    }

    /**
     * Retrieves an optional Empresa object based on the provided accountId.
     *
     * @param accountId the unique identifier of the Empresa to retrieve
     * @return an Optional containing the Empresa object if found, otherwise an empty Optional
     */
    @Override
    public Optional<Empresa> findById(String accountId) {
        return empresaRepository.findByAccountIdAndActiveTrue(accountId);
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    /**
        * Deletes an Empresa by its accountId.
        * Sets the active status of the Empresa to false.
        *
        * @param accountId the accountId of the Empresa to delete
        */
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