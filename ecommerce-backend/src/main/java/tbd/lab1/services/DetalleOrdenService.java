package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.DetalleOrdenEntity;
import tbd.lab1.repositories.DetalleOrdenRepository;

import java.util.ArrayList;

@Service
public class DetalleOrdenService {
    @Autowired
    DetalleOrdenRepository detalleOrdenRepository;

    public DetalleOrdenEntity saveDetalle(DetalleOrdenEntity detalle) {
        return detalleOrdenRepository.saveDetalleOrden(detalle);
    }

    public ArrayList<DetalleOrdenEntity> getDetalle() {
        return (ArrayList<DetalleOrdenEntity>) detalleOrdenRepository.getDetalleOrden();
    }
/*
    public DetalleOrdenEntity getDetalleById(Long id) {
        return detalleOrdenRepository.getDetalleOrdenById(id);
    }



    public boolean updateDetalle(DetalleOrdenEntity detalle) {
        // vemos si el cliente existe en la base de datos
        if (detalleOrdenRepository.getDetalleOrdenById(detalle.getIdDetalle()) != null) {
            // actualizamos el cliente usando el método del repositorio
            return detalleOrdenRepository.updateDetalleOrden(detalle);
        }
        return false;
    }

    public boolean deleteDetalle(Long id) throws Exception {
        try {
            detalleOrdenRepository.deleteDetalleOrden(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

 */
}
