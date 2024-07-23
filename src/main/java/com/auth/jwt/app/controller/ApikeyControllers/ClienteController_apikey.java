package com.auth.jwt.app.controller.ApikeyControllers;

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


import java.util.List;
import java.util.UUID;

/**
 * This class represents the controller for managing clients with APIKEY authentication.
 * It handles the creation, update, deletion, and retrieval of client information.
 */
@RestController
@Tag(name = "ApiKey", description = "Controller with APIKEY Authentication")
@RequestMapping("/apikey/clientes")
@SecurityRequirement(name = "apiKeyAuth")
public class ClienteController_apikey {

    @Autowired
    private ClienteService clienteService;

    /**
     * Creates a new client with the provided client data.
     *
     * @param crearClienteDTO The DTO object containing the client data.
     * @return A ResponseEntity with a success message if the client is created successfully.
     */
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

    /**
        * Updates a client with the given ID.
        *
        * @param id The ID of the client to update.
        * @param updateClientDTO The DTO containing the updated client information.
        * @return A ResponseEntity with a success message if the client was updated successfully, or a message indicating that the client was not found.
        */
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

    /**
     * Deletes a client based on the provided DTODeleteCliente object.
     * 
     * @param deleteClienteDTO The DTODeleteCliente object containing the client ID to be deleted.
     * @return A ResponseEntity<String> indicating the result of the deletion operation.
     */
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

    /**
     * Retrieves a Cliente object by its ID.
     *
     * @param id The ID of the Cliente object to retrieve.
     * @return ResponseEntity containing the Cliente object if found, or an ErrorResponse object if not found.
     */
    @GetMapping("/findby-Id/{id}")
    public ResponseEntity<?> findByIdClient(@PathVariable Long id) {
        Cliente cliente = clienteService.findbyIdClient(id);
        if (cliente == null) {
            ErrorResponse errorResponse=new ErrorResponse("Cliente no encontrado");
            return ResponseEntity.status(404).body(errorResponse);
        }
        return ResponseEntity.ok(cliente);
    }

    /**
     * Retrieves a list of clients by their name.
     *
     * @param nombre The name of the clients to search for.
     * @return A ResponseEntity containing a list of Cliente objects matching the given name.
     */
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
