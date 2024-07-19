package com.auth.jwt.app.controller.BasicControllers;

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

@RestController
@Tag(name = "Basic", description = "Controller with Basic Authentication")
@RequestMapping("/basic/accounts")
@SecurityRequirement(name = "basicAuth")
public class EmpresaController_basic {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController_basic(EmpresaService accountService) {
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