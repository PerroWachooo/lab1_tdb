package tbd.lab1.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import tbd.lab1.entities.ClienteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;

@Repository
public class ClienteRepository {

    private final DataSource dataSource;

    @Autowired
    public ClienteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // guarda un cliente sin jpa ;C
    public ClienteEntity saveCliente(ClienteEntity cliente) {
        String sql = "INSERT INTO cliente (nombre, direccion, email, telefono) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, cliente.getNombre());
            statement.setString(2, cliente.getDireccion());
            statement.setString(3, cliente.getEmail());
            statement.setString(4, cliente.getTelefono());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cliente.setIdCliente(generatedKeys.getLong(1));
                    }
                }
            } else {
                throw new SQLException("Error al insertar el cliente, no se generaron filas.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

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

}
