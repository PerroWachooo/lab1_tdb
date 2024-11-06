package tbd.lab1.repositories;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tbd.lab1.entities.ClienteEntity;

import java.sql.SQLException;
import java.util.List;
@Repository
public class ClienteRepository {

    @Autowired
    private Sql2o sql2o;

    // guarda un cliente sin jpa ;C
    // Guarda un cliente usando sql2o
    public ClienteEntity saveCliente(ClienteEntity cliente) {
        String sql = "INSERT INTO cliente (nombre, direccion, email, telefono) VALUES (:nombre, :direccion, :email, :telefono)";
        try (Connection con = sql2o.open()) {
            // Insertar el cliente en la base de datos
            Integer id = (Integer) con.createQuery(sql, true)  // true indica que se quiere obtener el ID generado
                    .addParameter("nombre", cliente.getNombre())
                    .addParameter("direccion", cliente.getDireccion())
                    .addParameter("email", cliente.getEmail())
                    .addParameter("telefono", cliente.getTelefono())
                    .executeUpdate()
                    .getKey(); // Obtener el ID generado

            // Establecer el ID generado al cliente
            cliente.setIdCliente(id);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return cliente;
    }
    /*
    // obtiene todos los clientes ingresados en la base de datos
    public ArrayList<ClienteEntity> getClientes() {
        ArrayList<ClienteEntity> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ClienteEntity cliente = new ClienteEntity();
                cliente.setIdCliente(resultSet.getLong("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setTelefono(resultSet.getString("telefono"));
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public boolean deleteCliente(Long id) throws Exception {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0; // Devuelve true si se eliminó al menos una fila

        } catch (SQLException e) {
            throw new Exception("Error al eliminar el cliente: " + e.getMessage());
        }
    }

    public ClienteEntity getClienteById(Long id) {
        String sql = "SELECT * FROM cliente WHERE id_cliente = ?";
        ClienteEntity cliente = null;
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
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

    public boolean updateCliente(ClienteEntity cliente) {
        String sql = "UPDATE cliente SET nombre = ?, direccion = ?, email = ?, telefono = ? WHERE id_cliente = ?";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());
            statement.setLong(5, cliente.getIdCliente());

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0; // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

     */
}
