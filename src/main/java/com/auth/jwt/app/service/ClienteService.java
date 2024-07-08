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

    @Override
    public List<Cliente> allClients() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente createClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente updateClient(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public void deleteClient(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public Cliente findbyIdClient(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Cliente> findbyNameClient(String nombre) {
        return clienteRepository.findByfirstname(nombre);
    }

    @Override
    public List<Cliente> findbyStatusClient(String status) {
        return clienteRepository.findBystatus(status);
    }

    @Override
    public List<Cliente> findbyCreateDateClient(Date creatDate) {
        return clienteRepository.findBycreateDate(creatDate);
    }

    @Override
    public List<Cliente> findbyLocationCodeClient(String codigoUbicacion) {
        return clienteRepository.findBylocationCode(codigoUbicacion);
    }
}
