package com.todocodeacademy.pruebaJPA.service;

import com.todocodeacademy.pruebaJPA.model.Cliente;
import com.todocodeacademy.pruebaJPA.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepo;

    @Override
    public void saveCliente(Cliente cli) {
        clienteRepo.save(cli);
    }

    @Override
    public List<Cliente> getClientes() {
        return clienteRepo.findAll();
    }

    @Override
    public Cliente findCliente(Long idCliente) {
        return clienteRepo.findById(idCliente).orElse(null);
    }

    @Override
    public void deleteCliente(Long idCliente) {
        clienteRepo.deleteById(idCliente);
    }

    @Override
    public Cliente editCliente(Long idCliente, Cliente cli) {
        Cliente cliente = this.findCliente(idCliente);

        if (cliente != null) {
            cliente.setNombre(cli.getNombre());
            cliente.setApellido(cli.getApellido());
            cliente.setDni(cli.getDni());
            return clienteRepo.save(cliente);
        }

        return null;
    }
}