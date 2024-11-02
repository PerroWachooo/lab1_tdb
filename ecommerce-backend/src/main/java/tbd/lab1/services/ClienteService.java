package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.repositories.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> getAllClientes() {
        return clienteRepository.getClientes();
    }

    public ClienteEntity getClienteById(Long id) {
        ClienteEntity cliente = clienteRepository.getClienteById(id);
        return cliente;
    }

    public ClienteEntity createCliente(ClienteEntity clienteEntity) {
        return clienteRepository.saveCliente(clienteEntity);
    }

    public boolean deleteCliente(Long id) {
        ClienteEntity cliente = clienteRepository.getClienteById(id);
        if (cliente != null) {
            try {
                clienteRepository.deleteCliente(id);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            return true;
        } else {
            return false;
        }
    }
}
