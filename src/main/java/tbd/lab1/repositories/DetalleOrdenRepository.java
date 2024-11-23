package tbd.lab1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tbd.lab1.entities.DetalleOrdenEntity;
import tbd.lab1.entities.OrdenEntity;
import tbd.lab1.entities.ProductoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class DetalleOrdenRepository {

/*
    @Autowired
    public DetalleOrdenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DetalleOrdenEntity saveDetalleOrden(DetalleOrdenEntity detalleOrden) {
        String sql = "INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?) RETURNING id_detalle";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Establecer valores en el PreparedStatement
            statement.setLong(1, detalleOrden.getOrden() != null ? detalleOrden.getOrden().getIdOrden() : null);
            statement.setLong(2, detalleOrden.getProducto() != null ? detalleOrden.getProducto().getIdProducto() : null);
            statement.setInt(3, detalleOrden.getCantidad());
            statement.setBigDecimal(4, detalleOrden.getPrecioUnitario());

            // Ejecutar la inserción y obtener el id_detalle generado
            try (ResultSet generatedKeys = statement.executeQuery()) {
                if (generatedKeys.next()) {
                    long idDetalle = generatedKeys.getLong(1);
                    // Asignar el idDetalle a la entidad y devolverla
                    detalleOrden.setIdDetalle(idDetalle);
                    return detalleOrden; // Devolver el detalle de orden guardado
                } else {
                    throw new SQLException("No se pudo obtener el id del detalle.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // En caso de error, devolver null
        }
    }

    public DetalleOrdenEntity getDetalleOrdenById(Long id) {
        String sql = "SELECT * FROM detalle_orden WHERE id_detalle = ?";
        DetalleOrdenEntity detalleOrden = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    detalleOrden = new DetalleOrdenEntity();
                    detalleOrden.setIdDetalle(resultSet.getLong("id_detalle"));
                    detalleOrden.setCantidad(resultSet.getInt("cantidad"));
                    detalleOrden.setPrecioUnitario(resultSet.getBigDecimal("precio_unitario"));

                    // Cargar la Orden
                    Long idOrden = resultSet.getLong("id_orden");
                    if (idOrden != null) {
                        OrdenEntity orden = new OrdenEntity();
                        orden.setIdOrden(idOrden);
                        detalleOrden.setOrden(orden);
                    }

                    // Cargar el Producto
                    Long idProducto = resultSet.getLong("id_producto");
                    if (idProducto != null) {
                        ProductoEntity producto = new ProductoEntity();
                        producto.setIdProducto(idProducto);
                        detalleOrden.setProducto(producto);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleOrden;
    }

    public ArrayList<DetalleOrdenEntity> getDetalleOrden() {
        ArrayList<DetalleOrdenEntity> detalleOrden = new ArrayList<>();
        String sql = "SELECT * FROM detalle_orden";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                DetalleOrdenEntity detallesOrden = new DetalleOrdenEntity();
                detallesOrden.setIdDetalle(resultSet.getLong("id_detalle"));
                detallesOrden.setCantidad(resultSet.getInt("cantidad"));
                detallesOrden.setPrecioUnitario(resultSet.getBigDecimal("precio_unitario"));

                // Cargar la Orden
                Long idOrden = resultSet.getLong("id_orden");
                if (idOrden != null) {
                    OrdenEntity orden = new OrdenEntity();
                    orden.setIdOrden(idOrden);
                    detallesOrden.setOrden(orden);
                }

                // Cargar el Producto
                Long idProducto = resultSet.getLong("id_producto");
                if (idProducto != null) {
                    ProductoEntity producto = new ProductoEntity();
                    producto.setIdProducto(idProducto);
                    detallesOrden.setProducto(producto);
                }

                detalleOrden.add(detallesOrden);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detalleOrden;
    }

    public boolean updateDetalleOrden(DetalleOrdenEntity detalleOrden) {
        String sql = "UPDATE detalle_orden SET cantidad = ?, precio_unitario = ?, id_orden = ?, id_producto = ? WHERE id_detalle = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, detalleOrden.getCantidad());
            statement.setBigDecimal(2, detalleOrden.getPrecioUnitario());

            // Verificar si el detalle tiene una orden asignada
            if (detalleOrden.getOrden() != null) {
                statement.setLong(3, detalleOrden.getOrden().getIdOrden());
            } else {
                statement.setNull(3, java.sql.Types.BIGINT); // Si no hay orden, se guarda como NULL
            }

            // Verificar si el detalle tiene un producto asignado
            if (detalleOrden.getProducto() != null) {
                statement.setLong(4, detalleOrden.getProducto().getIdProducto());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT); // Si no hay producto, se guarda como NULL
            }

            statement.setLong(5, detalleOrden.getIdDetalle()); // Establece el id del detalle

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteDetalleOrden(Long id) throws Exception {
        String sql = "DELETE FROM detalle_orden WHERE id_detalle = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0; // Devuelve true si se eliminó al menos una fila

        } catch (SQLException e) {
            throw new Exception("Error al eliminar el detalle de la orden: " + e.getMessage());
        }
    }

*/



}
