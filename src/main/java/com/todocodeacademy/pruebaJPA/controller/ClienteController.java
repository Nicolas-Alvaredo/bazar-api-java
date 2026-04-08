package com.todocodeacademy.pruebaJPA.controller;

import com.todocodeacademy.pruebaJPA.model.Cliente;
import com.todocodeacademy.pruebaJPA.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    @Autowired
    private IClienteService clienteServ;

    @PostMapping("/crear")
    public String crearCliente(@RequestBody Cliente cli) {
        clienteServ.saveCliente(cli);
        return "Cliente creado correctamente";
    }

    @GetMapping
    public List<Cliente> traerClientes() {
        return clienteServ.getClientes();
    }

    @GetMapping("/{id_cliente}")
    public Cliente traerCliente(@PathVariable("id_cliente") Long idCliente) {
        return clienteServ.findCliente(idCliente);
    }

    @DeleteMapping("/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable("id_cliente") Long idCliente) {
        clienteServ.deleteCliente(idCliente);
        return "Cliente eliminado correctamente";
    }

    @PutMapping("/editar/{id_cliente}")
    public Cliente editarCliente(@PathVariable("id_cliente") Long idCliente,
                                 @RequestBody Cliente cli) {
        return clienteServ.editCliente(idCliente, cli);
    }
}