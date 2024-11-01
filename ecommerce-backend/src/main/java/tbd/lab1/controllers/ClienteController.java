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

<<<<<<< HEAD
    //crea un cliente
    @PostMapping("/")
    public ResponseEntity<ClienteEntity> saveCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity NewCliente = clienteService.saveCliente(cliente);
        return ResponseEntity.ok(NewCliente);
    }
    //obtiene todos los clientes ingresados en la base de datos
    @GetMapping("/")
    public ResponseEntity<List<ClienteEntity>> listCliente() {
        List<ClienteEntity> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
=======

    @GetMapping("")
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        return ResponseEntity.ok().body(clienteService.getAllClientes());
>>>>>>> Andre
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

<<<<<<< HEAD
    //actualiza cliente
    @PutMapping("/")
    public ResponseEntity<ClienteEntity> updateCliente(@RequestBody ClienteEntity cliente) {
        boolean isUpdated = clienteService.updateCliente(cliente);
        if (isUpdated) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
=======
    // Crea un cliente
    @PostMapping("")
    public ResponseEntity<ClienteEntity> createCliente(@RequestBody ClienteEntity cliente) {
        ClienteEntity newCliente = clienteService.createCliente(cliente);
        return ResponseEntity.ok(newCliente);
>>>>>>> Andre
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
<<<<<<< HEAD

=======
>>>>>>> Andre
}
