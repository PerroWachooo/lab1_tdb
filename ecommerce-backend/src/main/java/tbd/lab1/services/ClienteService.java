package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.repositories.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteEntity saveCliente(ClienteEntity cliente){
        return clienteRepository.saveCliente(cliente);
    }

    public ArrayList<ClienteEntity> getClientes(){
        return (ArrayList<ClienteEntity>) clienteRepository.getClientes();
    }

    public boolean deleteCliente(Long id) throws Exception {
        try{
            clienteRepository.deleteCliente(id);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ClienteEntity getClienteById(Long id){
        return clienteRepository.getClienteById(id);
    }

    public boolean updateCliente(ClienteEntity cliente) {
        // vemos si el cliente existe en la base de datos
        if (clienteRepository.getClienteById(cliente.getIdCliente()) != null) {
            // actualizamos el cliente usando el método del repositorio
            return clienteRepository.updateCliente(cliente);
        }
        return false;
    }

}
