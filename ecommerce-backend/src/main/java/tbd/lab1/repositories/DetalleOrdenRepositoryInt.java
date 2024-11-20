package tbd.lab1.repositories;
import tbd.lab1.entities.DetalleOrdenEntity;
import java.util.List;

public interface DetalleOrdenRepositoryInt {

    DetalleOrdenEntity saveDetalleOrden(DetalleOrdenEntity detalleOrden);

    List<DetalleOrdenEntity> getDetalleOrden();

    DetalleOrdenEntity getDetalleOrdenById(Integer id);

    boolean updateDetalleOrden(DetalleOrdenEntity detalleOrden);

    boolean deleteDetalleOrden(Integer id);
}
