package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.OrdenEntity;
import tbd.lab1.entities.ProductoEntity;
import tbd.lab1.repositories.OrdenRepository;
import tbd.lab1.repositories.ProductoRepository;

import java.util.ArrayList;

@Service
public class OrdenService {
    @Autowired
    OrdenRepository ordenRepository;
    public OrdenEntity saveOrden(OrdenEntity orden){
        return ordenRepository.saveOrden(orden);
    }

    public OrdenEntity getOrdenById(Long id){
        return ordenRepository.getOrdenById(id);
    }

    public ArrayList<OrdenEntity> getOrdenes(){
        return (ArrayList<OrdenEntity>) ordenRepository.getOrdenes();
    }

    public boolean updateOrden(OrdenEntity orden) {
        // vemos si el cliente existe en la base de datos
        if (ordenRepository.getOrdenById(orden.getIdOrden()) != null) {
            // actualizamos el cliente usando el método del repositorio
            return ordenRepository.updateOrden(orden);
        }
        return false;
    }

    public boolean deleteOrden(Long id) throws Exception {
        try{
            ordenRepository.deleteOrden(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
