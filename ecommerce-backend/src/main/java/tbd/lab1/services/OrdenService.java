package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.OrdenEntity;
import tbd.lab1.repositories.OrdenRepository;


import java.util.ArrayList;

@Service
public class OrdenService {
    @Autowired
    OrdenRepository ordenRepository;
    public OrdenEntity saveOrden(OrdenEntity orden){
        return ordenRepository.saveOrden(orden);
    }

    public OrdenEntity getOrdenById(Integer id){
        return ordenRepository.getOrdenById(id);
    }

    public ArrayList<OrdenEntity> getOrdenes(){
        return (ArrayList<OrdenEntity>) ordenRepository.getOrdenes();
    }

    public boolean updateOrden(OrdenEntity orden) {
        // vemos si el cliente existe en la base de datos
        if (ordenRepository.getOrdenById(orden.getId_orden()) != null) {
            // actualizamos el cliente usando el método del repositorio
            return ordenRepository.updateOrden(orden);
        }
        return false;
    }

    public boolean deleteOrden(Integer id) throws Exception {
        try{
            ordenRepository.deleteOrden(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
