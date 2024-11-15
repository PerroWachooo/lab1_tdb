package tbd.lab1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;
import tbd.lab1.entities.DetalleOrdenEntity;
import tbd.lab1.entities.OrdenEntity;
import tbd.lab1.entities.ProductoEntity;
import org.sql2o.Connection;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import java.sql.SQLException;

@Repository
public class DetalleOrdenRepository {

    @Autowired
    private Sql2o sql2o;

    public DetalleOrdenEntity saveDetalleOrden(DetalleOrdenEntity detalleOrden) {
        String sql = "INSERT INTO detalle_orden (id_orden, id_producto, cantidad, precio_unitario) VALUES (:idOrden, :idProducto, :cantidad, :precioUnitario) RETURNING id_detalle";

        try (Connection con = sql2o.open()) {
            // Ejecutar la consulta de inserción y obtener el id_detalle generado
            Integer idDetalle = con.createQuery(sql)
                    .addParameter("idOrden", detalleOrden.getId_orden() != null ? detalleOrden.getId_orden() : null)
                    .addParameter("idProducto", detalleOrden.getId_producto() != null ? detalleOrden.getId_producto() : null)
                    .addParameter("cantidad", detalleOrden.getCantidad())
                    .addParameter("precioUnitario", detalleOrden.getPrecio_unitario())
                    .executeUpdate()
                    .getKey(Integer.class);

            if (idDetalle == null) {
                throw new RuntimeException("Failed to insert DetalleOrden. No ID returned.");
            }

            detalleOrden.setId_detalle(idDetalle);
            return detalleOrden;

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error occurred while saving DetalleOrden: " + e.getMessage());
            return null; // En caso de error, devolver null
        }
    }

    public List<DetalleOrdenEntity> getDetalleOrden() {
        String sql = "SELECT id_detalle, id_orden, id_producto, cantidad, precio_unitario FROM detalle_orden";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(DetalleOrdenEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


/*
    @Autowired
    public DetalleOrdenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
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
