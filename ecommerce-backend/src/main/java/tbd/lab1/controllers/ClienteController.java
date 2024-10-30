package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.services.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
@CrossOrigin("*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @GetMapping("")
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        return ResponseEntity.ok().body(clienteService.getAllClientes());
    }

    // Obtiene un cliente específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<ClienteEntity> getClienteById(@PathVariable Long id) {
        ClienteEntity cliente = clienteService.getClienteById(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crea un cliente
    @PostMapping("")
    public ResponseEntity<ClienteEntity> createCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity newCliente = clienteService.createCliente(cliente);
        return ResponseEntity.ok(newCliente);
    }

    // Borra un cliente específico por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id) {
        boolean isDeleted = clienteService.deleteCliente(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
