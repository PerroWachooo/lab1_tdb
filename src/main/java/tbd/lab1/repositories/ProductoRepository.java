package tbd.lab1.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.entities.ProductoEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class ProductoRepository {
    private final DataSource dataSource;

    @Autowired
    public ProductoRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public ProductoEntity saveProducto(ProductoEntity producto) {
        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, estado, id_categoria) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setBigDecimal(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setString(5, producto.getEstado());

            // verificamos que producto tiene una categoría con un ID establecido
            if (producto.getCategoria() != null && producto.getCategoria().getIdCategoria() != null) {
                statement.setLong(6, producto.getCategoria().getIdCategoria());
            } else {
                throw new SQLException("Categoría no proporcionada o inválida");
            }

            int affectedRows = statement.executeUpdate();

            // Obtener el ID generado para el producto
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        producto.setIdProducto(generatedKeys.getLong(1));
                    }
                }
            } else {
                throw new SQLException("Error al insertar el producto, no se generaron filas.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public ProductoEntity getProductoById(Long id) {
        String sql = "SELECT * FROM producto WHERE id_producto = ?";
        ProductoEntity producto = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    producto = new ProductoEntity();
                    producto.setIdProducto(resultSet.getLong("id_producto"));
                    producto.setNombre(resultSet.getString("nombre"));
                    producto.setDescripcion(resultSet.getString("descripcion"));
                    producto.setPrecio(resultSet.getBigDecimal("precio"));
                    producto.setStock(resultSet.getInt("stock"));
                    producto.setEstado(resultSet.getString("estado"));
                    // Cargar la categoría
                    Long idCategoria = resultSet.getLong("id_categoria");
                    if (idCategoria != null) {
                        CategoriaEntity categoria = new CategoriaEntity();
                        categoria.setIdCategoria(idCategoria);
                        producto.setCategoria(categoria);
                    }

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producto;
    }

    public ArrayList<ProductoEntity> getProductos() {
        ArrayList<ProductoEntity> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductoEntity producto = new ProductoEntity();
                producto.setIdProducto(resultSet.getLong("id_producto"));
                producto.setNombre(resultSet.getString("nombre"));
                producto.setDescripcion(resultSet.getString("descripcion"));
                producto.setPrecio(resultSet.getBigDecimal("precio"));
                producto.setStock(resultSet.getInt("stock"));
                producto.setEstado(resultSet.getString("estado"));

                Long idCategoria = resultSet.getLong("id_categoria");
                if (idCategoria != null) {
                    CategoriaEntity categoria = new CategoriaEntity();
                    categoria.setIdCategoria(idCategoria);
                    producto.setCategoria(categoria); // Asignar la categoría al producto
                }

                productos.add(producto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    public boolean updateProducto(ProductoEntity producto) {
        String sql = "UPDATE producto SET nombre = ?, descripcion = ?, precio = ?, stock = ?,  estado = ?, id_categoria = ? WHERE id_producto = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, producto.getNombre());
            statement.setString(2, producto.getDescripcion());
            statement.setBigDecimal(3, producto.getPrecio());
            statement.setInt(4, producto.getStock());
            statement.setString(5, producto.getEstado());

            // Verificar si el producto tiene una categoría asignada
            if (producto.getCategoria() != null) {
                statement.setLong(6, producto.getCategoria().getIdCategoria());
            } else {
                statement.setNull(6, java.sql.Types.BIGINT); // Si no hay categoría, se guarda como NULL
            }

            statement.setLong(7, producto.getIdProducto());  // Establece el id del producto


            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;  // Devuelve true si se actualizó al menos una fila

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteProducto(Long id) throws Exception {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();

            return affectedRows > 0; // Devuelve true si se eliminó al menos una fila

        } catch (SQLException e) {
            throw new Exception("Error al eliminar el producto: " + e.getMessage());
        }
    }

}
