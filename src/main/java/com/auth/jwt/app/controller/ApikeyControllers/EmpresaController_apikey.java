package com.auth.jwt.app.controller.ApikeyControllers;

import com.auth.jwt.app.entity.Empresa;
import com.auth.jwt.app.payload.DTOEmpresa;
import com.auth.jwt.app.service.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;
import java.util.Optional;

/**
 * This class represents the controller for managing Empresa accounts with APIKEY authentication.
 * It provides endpoints for retrieving, creating, updating, and deleting Empresa accounts.
 */
@RestController
@Tag(name = "ApiKey", description = "Controller with APIKEY Authentication")
@RequestMapping("/apikey/accounts")
@SecurityRequirement(name = "apiKeyAuth")
public class EmpresaController_apikey {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController_apikey(EmpresaService accountService) {
        this.empresaService = accountService;
    }

    @GetMapping
    public List<Empresa> getAllAccounts() {
        return empresaService.findAll();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Empresa> getAccountById(@PathVariable String accountId) {
        Optional<Empresa> account = empresaService.findById(accountId);
        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Creates a new Empresa (company) based on the provided DTOEmpresa object.
     *
     * @param crearEmpresaDTO The DTOEmpresa object containing the details of the Empresa to be created.
     * @return A ResponseEntity with a success message if the Empresa is created successfully.
     */
    @PostMapping("/createacc")
    public ResponseEntity<String> crearEmpresa(@RequestBody DTOEmpresa crearEmpresaDTO) {
        Empresa nuevaEmpresa = new Empresa();
        nuevaEmpresa.setAccountId(crearEmpresaDTO.getAccountId());
        nuevaEmpresa.setType(crearEmpresaDTO.getType());
        nuevaEmpresa.setAccount(crearEmpresaDTO.getAccount());
        nuevaEmpresa.setMainPhone(crearEmpresaDTO.getMainPhone());
        nuevaEmpresa.setEmail(crearEmpresaDTO.getEmail());

        Empresa empresaCreado = empresaService.save(nuevaEmpresa);
        return ResponseEntity.ok("Empresa creado correctamente");
    }

    /**
     * Updates an account with the specified accountId using the provided updateEmpresaDTO.
     *
     * @param accountId        The ID of the account to be updated.
     * @param updateEmpresaDTO The DTO containing the updated account details.
     * @return A ResponseEntity containing the updated Empresa object if the account exists, or a ResponseEntity with status 404 if the account does not exist.
     */
    @PutMapping("/{accountId}")
    public ResponseEntity<Empresa> updateAccount(@PathVariable String accountId, @RequestBody DTOEmpresa updateEmpresaDTO) {
        Optional<Empresa> accountOptional = empresaService.findById(accountId);
        if (accountOptional.isPresent()) {
            Empresa empresa = accountOptional.get();
            // Update the fields of the account with accountDetails here
            empresa.setAccountId(updateEmpresaDTO.getAccountId());
            empresa.setType(updateEmpresaDTO.getType());
            empresa.setAccount(updateEmpresaDTO.getAccount());
            empresa.setMainPhone(updateEmpresaDTO.getMainPhone());
            empresa.setEmail(updateEmpresaDTO.getEmail());
            final Empresa updatedEmpresa = empresaService.save(empresa);
            return ResponseEntity.ok(updatedEmpresa);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String accountId) {
        empresaService.deleteById(accountId);
        return ResponseEntity.ok().build();
    }
}