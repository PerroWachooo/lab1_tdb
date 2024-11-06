package tbd.lab1.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.entities.OrdenEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Repository
public class OrdenRepository {
/*
    private static final Logger logger = LoggerFactory.getLogger(OrdenRepository.class);

    private final DataSource dataSource;

    @Autowired
    public OrdenRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public OrdenEntity saveOrden(OrdenEntity orden) {
        logger.info("Recibido objeto Orden3: {}", orden);
        String sql = "INSERT INTO orden (fecha_orden, estado, id_cliente, total) VALUES (?, ?, ?, ?) RETURNING id_orden";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            logger.info("Recibido objeto Orden4: {}", orden);
            // Establecer valores en el PreparedStatement
            statement.setObject(1, orden.getFechaOrden());
            statement.setString(2, orden.getEstado());
            statement.setLong(3, orden.getCliente() != null ? orden.getCliente().getIdCliente() : null);
            statement.setBigDecimal(4, orden.getTotal());
            logger.info("Recibido objeto Orden5: {}", orden);
            // Ejecutar la inserción y obtener el id_orden generado
            try (ResultSet generatedKeys = statement.executeQuery()) {
                if (generatedKeys.next()) {
                    long idOrden = generatedKeys.getLong(1);
                    // Asignar el idOrden a la entidad y devolverla
                    logger.info("Recibido objeto Orden6: {}", orden);
                    orden.setIdOrden(idOrden);
                    return orden; // Devolver la orden guardada
                } else {
                    throw new SQLException("No se pudo obtener el id de la orden.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null; // En caso de error, devolver null
        }
    }

    public OrdenEntity getOrdenById(Long id) {
        String sql = "SELECT * FROM orden WHERE id_orden = ?";
        OrdenEntity orden = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    orden = new OrdenEntity();
                    orden.setIdOrden(resultSet.getLong("id_orden"));
                    orden.setFechaOrden(resultSet.getObject("fecha_orden", LocalDateTime.class)); // Cambiar según el
                                                                                                  // tipo de dato
                    orden.setEstado(resultSet.getString("estado"));
                    orden.setTotal(resultSet.getBigDecimal("total"));
                    // Asegúrate de que la columna total esté presente en tu consulta
                    // Cargar el cliente asociado
                    ClienteEntity cliente = new ClienteEntity();
                    cliente.setIdCliente(resultSet.getLong("id_cliente"));
                    orden.setCliente(cliente);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orden;
    }

    public ArrayList<OrdenEntity> getOrdenes() {
        ArrayList<OrdenEntity> ordenes = new ArrayList<>();
        String sql = "SELECT * FROM orden";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                OrdenEntity orden = new OrdenEntity();
                orden.setIdOrden(resultSet.getLong("id_orden"));
                orden.setFechaOrden(resultSet.getObject("fecha_orden", LocalDateTime.class));
                orden.setEstado(resultSet.getString("estado"));
                orden.setTotal(resultSet.getBigDecimal("total"));

                Long idCliente = resultSet.getLong("id_cliente");
                ClienteEntity cliente = getClienteById(idCliente);
                orden.setCliente(cliente);

                ordenes.add(orden);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordenes;
    }

    private ClienteEntity getClienteById(Long idCliente) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        ClienteEntity cliente = null;

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, idCliente);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cliente = new ClienteEntity();
                    cliente.setIdCliente(resultSet.getLong("id_cliente"));
                    cliente.setNombre(resultSet.getString("nombre"));
                    cliente.setDireccion(resultSet.getString("direccion"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setTelefono(resultSet.getString("telefono"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    public boolean updateOrden(OrdenEntity orden) {
        String sql = "UPDATE orden SET fecha_orden = ?, estado = ?, total = ?, id_cliente = ? WHERE id_orden = ?";

        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            // Establecer los valores para la consulta
            statement.setObject(1, orden.getFechaOrden());
            statement.setString(2, orden.getEstado());
            statement.setBigDecimal(3, orden.getTotal());

            // Verificar si la orden tiene un cliente asignado
            if (orden.getCliente() != null) {
                statement.setLong(4, orden.getCliente().getIdCliente());
            } else {
                statement.setNull(4, java.sql.Types.BIGINT); // Si no hay cliente, se guarda como NULL
            }

            statement.setLong(5, orden.getIdOrden()); // Establece el id de la orden

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false; // Devuelve false si ocurre un error o no se actualiza ninguna fila
    }

    public boolean deleteOrden(Long id) throws Exception {
        String sql = "DELETE FROM orden WHERE id_orden = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0; // Devuelve true si se eliminó al menos una fila

        } catch (SQLException e) {
            throw new Exception("Error al eliminar la orden: " + e.getMessage());
        }
    }
 */
}
