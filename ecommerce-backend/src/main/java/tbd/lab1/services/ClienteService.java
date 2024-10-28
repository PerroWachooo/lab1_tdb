package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.repositories.ClienteRepository;

import java.util.ArrayList;

@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;

    public ClienteEntity saveCliente(ClienteEntity cliente){
        return clienteRepository.save(cliente);
    }

    public ArrayList<ClienteEntity> getClientes(){
        return (ArrayList<ClienteEntity>) clienteRepository.findAll();
    }

    public boolean deleteCliente(Long id) throws Exception {
        try{
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClienteEntity getClienteById(Long id){
        return clienteRepository.findById(id).orElse(null);
    }
}