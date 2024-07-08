package com.auth.jwt.app.service;

import com.auth.jwt.app.entity.Cliente;

import java.util.Date;
import java.util.List;

public interface IClienteService {
    /*Metodos gestion*/
    List<Cliente> allClients();
    Cliente createClient(Cliente cliente);
    Cliente updateClient(Cliente cliente);
    void deleteClient(Long id);
    /*Metodos por busqed y filtrado*/
    Cliente findbyIdClient(Long id);
    List<Cliente> findbyNameClient(String firstname);
    List<Cliente> findbyStatusClient(String status);
    List<Cliente> findbyCreateDateClient(Date creatDate);
    List<Cliente> findbyLocationCodeClient(String locationCode);
}
