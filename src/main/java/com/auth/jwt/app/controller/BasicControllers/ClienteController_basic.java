package com.auth.jwt.app.controller.BasicControllers;

import com.auth.jwt.app.entity.Cliente;
import com.auth.jwt.app.payload.DTOCrearCliente;
import com.auth.jwt.app.payload.DTODeleteCliente;
import com.auth.jwt.app.payload.DTOUpdateClient;
import com.auth.jwt.app.payload.ErrorResponse;
import com.auth.jwt.app.service.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Basic", description = "Controller with Basic Authentication")
@RequestMapping("/basic/clientes")
@SecurityRequirement(name = "basicAuth")
public class ClienteController_basic {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearCliente(@RequestBody DTOCrearCliente crearClienteDTO) {
        Cliente nuevoCliente = new Cliente();
//      /*Valores autogenerados*/
        java.util.Date createDateToday = new java.util.Date(); // Fecha actual
        String secCodeId = UUID.randomUUID().toString();
//      /* Captura de informacion al objeto */
        nuevoCliente.setFirstname(crearClienteDTO.getFirstname());
        nuevoCliente.setMiddlename(crearClienteDTO.getMiddlename());
        nuevoCliente.setLastname(crearClienteDTO.getLastname());
        nuevoCliente.setBirthday(crearClienteDTO.getBirthday());
        nuevoCliente.setCreateDate(createDateToday);
        nuevoCliente.setEmail(crearClienteDTO.getEmail());
        nuevoCliente.setMobile(crearClienteDTO.getMobile());
        nuevoCliente.setHomephone(crearClienteDTO.getHomephone());
        nuevoCliente.setNationality(crearClienteDTO.getNationality());
        nuevoCliente.setSecCodeId(secCodeId);
        /* Envia informacion a base de datos*/
        Cliente clienteCreado = clienteService.createClient(nuevoCliente);
        return ResponseEntity.ok("Cliente creado correctamente");
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> actualizarCliente(@PathVariable Long id, @RequestBody DTOUpdateClient updateClientDTO) {
        Cliente clienteExistente = clienteService.findbyIdClient(id);
        if (clienteExistente == null) {
            return ResponseEntity.ok("Cliente no encontrado");
        }

        clienteExistente.setFirstname(updateClientDTO.getFirstname());
        clienteExistente.setMiddlename(updateClientDTO.getMiddlename());
        clienteExistente.setLastname(updateClientDTO.getLastname());
        clienteExistente.setBirthday(updateClientDTO.getBirthday());
        clienteExistente.setCreateDate(updateClientDTO.getCreateDate());
        clienteExistente.setEmail(updateClientDTO.getEmail());
        clienteExistente.setMobile(updateClientDTO.getMobile());
        clienteExistente.setHomephone(updateClientDTO.getHomephone());
        clienteExistente.setNationality(updateClientDTO.getNationality());
        clienteExistente.setSecCodeId(updateClientDTO.getSecCodeId());

        Cliente clienteActualizado = clienteService.updateClient(clienteExistente);
        return ResponseEntity.ok("Cliente Actualizado");
    }

    @PostMapping("/eliminar")
    public ResponseEntity<String> eliminarCliente(@RequestBody DTODeleteCliente deleteClienteDTO) {
        Cliente clienteExistente = clienteService.findbyIdClient(deleteClienteDTO.getClienteid());
        if (clienteExistente == null) {
            return ResponseEntity.ok("Cliente no encontrado");
        }

        clienteExistente.setStatus(false);
        clienteService.updateClient(clienteExistente);
        return ResponseEntity.ok("Cliente eliminado");
    }

    @GetMapping("/findby-Id/{id}")
    public ResponseEntity<?> findByIdClient(@PathVariable Long id) {
        Cliente cliente = clienteService.findbyIdClient(id);
        if (cliente == null) {
            ErrorResponse errorResponse=new ErrorResponse("Cliente no encontrado");
            return ResponseEntity.status(404).body(errorResponse);
        }
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/findby-nombre/{nombre}")
    public ResponseEntity<List<Cliente>> findByNameClient(@PathVariable String nombre) {
        List<Cliente> clientes = clienteService.findbyNameClient(nombre);
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/findby-estado/{estado}")
    public ResponseEntity<List<Cliente>> findByStatusClient(@PathVariable String estado) {
        List<Cliente> clientes = clienteService.findbyStatusClient(estado);
        return ResponseEntity.ok(clientes);
    }

//    @GetMapping("/fecha-creacion/{fecha}")
//    public ResponseEntity<List<Cliente>> findByCreateDateClient(@PathVariable String fecha) {
//        Date fechaCreacion = Date.valueOf(fecha);
//        List<Cliente> clientes = clienteService.findbyCreateDateClient(fechaCreacion);
//        return ResponseEntity.ok(clientes);
//    }
//
//    @GetMapping("/codigo-ubicacion/{codigo}")
//    public ResponseEntity<List<Cliente>> findByLocationCodeClient(@PathVariable String codigo) {
//        List<Cliente> clientes = clienteService.findbyLocationCodeClient(codigo);
//        return ResponseEntity.ok(clientes);
//    }
}
