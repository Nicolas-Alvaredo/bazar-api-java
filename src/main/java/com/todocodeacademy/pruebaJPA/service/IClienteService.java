package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Cliente;
import java.util.List;

public interface IClienteService {

    public void saveCliente(Cliente cli);

    public List<Cliente> getClientes();

    public Cliente findCliente(Long idCliente);

    public void deleteCliente(Long idCliente);

    public Cliente editCliente(Long idCliente, Cliente cli);
}