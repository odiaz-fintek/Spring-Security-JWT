package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Cliente;
import com.auth.jwt.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Retrieves all clients from the database.
     *
     * @return a list of all clients
     */
    @Override
    public List<Cliente> allClients() {
        return clienteRepository.findAll();
    }

    /**
     * Creates a new client in the database.
     *
     * @param cliente the client to be created
     * @return the created client
     */
    @Override
    public Cliente createClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Updates an existing client in the database.
     *
     * @param cliente the client to be updated
     * @return the updated client
     */
    @Override
    public Cliente updateClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Deletes a client from the database based on the given ID.
     *
     * @param id the ID of the client to be deleted
     */
    @Override
    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }

    /**
     * Retrieves a client from the database based on the given ID.
     *
     * @param id the ID of the client to be retrieved
     * @return the retrieved client, or null if not found
     */
    @Override
    public Cliente findbyIdClient(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    /**
     * Retrieves clients from the database based on the given name.
     *
     * @param nombre the name of the clients to be retrieved
     * @return a list of clients with the given name
     */
    @Override
    public List<Cliente> findbyNameClient(String nombre) {
        return clienteRepository.findByfirstname(nombre);
    }

    /**
     * Retrieves clients from the database based on the given status.
     *
     * @param status the status of the clients to be retrieved
     * @return a list of clients with the given status
     */
    @Override
    public List<Cliente> findbyStatusClient(String status) {
        return clienteRepository.findBystatus(status);
    }

    /**
     * Retrieves clients from the database based on the given create date.
     *
     * @param createDate the create date of the clients to be retrieved
     * @return a list of clients with the given create date
     */
    @Override
    public List<Cliente> findbyCreateDateClient(Date createDate) {
        return clienteRepository.findBycreateDate(createDate);
    }

    /**
     * Retrieves clients from the database based on the given location code.
     *
     * @param codigoUbicacion the location code of the clients to be retrieved
     * @return a list of clients with the given location code
     */
    @Override
    public List<Cliente> findbyLocationCodeClient(String codigoUbicacion) {
        return clienteRepository.findBylocationCode(codigoUbicacion);
    }
}
