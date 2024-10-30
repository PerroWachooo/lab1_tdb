package tbd.lab1.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import tbd.lab1.entities.CategoriaEntity;
import org.springframework.beans.factory.annotation.Autowired;


import javax.sql.DataSource;

@Repository
public class CategoriaRepository{
    private final DataSource dataSource;

    @Autowired
    public CategoriaRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CategoriaEntity saveCategoria(CategoriaEntity categoria) {
        String sql = "INSERT INTO categoria (nombre) VALUES (?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, categoria.getNombre());

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categoria.setIdCategoria(generatedKeys.getLong(1));
                    }
                }
            } else {
                throw new SQLException("Error al insertar la categoria, no se generaron filas.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public CategoriaEntity findByIdCategoria(int id) {
        String sql = "SELECT * FROM categoria WHERE id_categoria = ?";
        CategoriaEntity categoria = null;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    categoria = new CategoriaEntity();
                    categoria.setIdCategoria(resultSet.getLong("id_categoria"));
                    categoria.setNombre(resultSet.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }
}
