package tbd.lab1.repositories;

import tbd.lab1.entities.DetalleOrdenEntity;

import java.util.List;

public interface DetalleOrdenRepositoryInt {
    DetalleOrdenEntity saveDetalleOrden(DetalleOrdenEntity detalleOrden);
    DetalleOrdenEntity getDetalleOrdenById(Integer id);
    List<DetalleOrdenEntity> getDetalleOrdenes();
    boolean updateDetalleOrden(DetalleOrdenEntity detalleOrden);
    boolean deleteDetalleOrden(Integer id);
}
