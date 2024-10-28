package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    //obtiene todos los clientes ingresados en la base de datos
    @GetMapping("/")
    public ResponseEntity<List<ClienteEntity>> listCliente() {
        List<ClienteEntity> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/id-cliente/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.getClienteById(id);
        return ResponseEntity.ok(cliente);
    }

    //crea un cliente
    @PostMapping("/")
    public ResponseEntity<ClienteEntity> saveCliente(@RequestBody ClienteEntity cliente) {
            ClienteEntity NewCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(NewCliente);
    }

    //borra un solo cliente
    @DeleteMapping("/delete-cliente/{id}")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable Long id) throws Exception {
        var isDeleted = clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }










}

