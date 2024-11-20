package tbd.lab1.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Sql2o;

import tbd.lab1.entities.OrdenEntity;
import tbd.lab1.entities.ProductoEntity;
import org.sql2o.Connection;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductoRepository implements ProductoRepositoryInt{

    @Autowired
    private Sql2o sql2o;

    public ProductoEntity saveProducto(ProductoEntity producto) {
        // Validar que los campos necesarios no estén nulos
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto es obligatoria.");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero.");
        }
        if (producto.getStock() == null || producto.getStock() <= 0) {
            throw new IllegalArgumentException("El stock del producto debe ser mayor a cero.");
        }
        if (producto.getEstado() == null ||
                (!producto.getEstado().equals("disponible") && !producto.getEstado().equals("agotado"))) {
            throw new IllegalArgumentException("El estado debe ser 'disponible' o 'agotado'.");
        }
        if (producto.getId_categoria() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }

        String sql = "INSERT INTO producto (nombre, descripcion, precio, stock, estado, id_categoria) " +
                "VALUES (:nombre, :descripcion, :precio, :stock, :estado, :id_categoria) " +
                "RETURNING id_producto";

        try (Connection con = sql2o.open()) {
            // Ejecutar la consulta e intentar obtener el producto insertado
            Integer newProducto = con.createQuery(sql, true)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria()) // Se asume que id_categoria no es nulo aquí
                    .executeUpdate()
                    .getKey(Integer.class);

            producto.setIdProducto(newProducto);
            return producto;

        } catch (Exception e) {
            e.printStackTrace();
            return null; // En caso de error, devolver null
        }
    }

    public List<ProductoEntity> getProductos() {
        String sql = "SELECT p.id_producto AS idProducto, p.nombre AS nombre, p.descripcion AS descripcion, " +
                "p.precio AS precio, p.stock AS stock, p.estado AS estado, p.id_categoria AS id_categoria " +
                "FROM producto p";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(ProductoEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ProductoEntity getProductoById(Integer id) {
        String sql = "SELECT p.id_producto AS idProducto, p.nombre AS nombre, p.descripcion AS descripcion, " +
                "p.precio AS precio, p.stock AS stock, p.estado AS estado, p.id_categoria AS id_categoria " +
                "FROM producto p " +
                "WHERE p.id_producto = :id";

        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(ProductoEntity.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateProducto(ProductoEntity producto) {
        // Validar que los campos necesarios no estén nulos
        if (producto.getIdProducto() == null) {
            throw new IllegalArgumentException("El ID del producto es obligatorio.");
        }
        if (producto.getNombre() == null || producto.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre del producto es obligatorio.");
        }
        if (producto.getDescripcion() == null || producto.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción del producto es obligatoria.");
        }
        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio del producto debe ser mayor a cero.");
        }
        if (producto.getStock() == null || producto.getStock() <= 0) {
            throw new IllegalArgumentException("El stock del producto debe ser mayor a cero.");
        }
        if (producto.getEstado() == null ||
                (!producto.getEstado().equals("disponible") && !producto.getEstado().equals("agotado"))) {
            throw new IllegalArgumentException("El estado debe ser 'disponible' o 'agotado'.");
        }
        if (producto.getId_categoria() == null) {
            throw new IllegalArgumentException("La categoría es obligatoria.");
        }

        String sql = "UPDATE producto SET nombre = :nombre, descripcion = :descripcion, " +
                "precio = :precio, stock = :stock, estado = :estado, id_categoria = :id_categoria " +
                "WHERE id_producto = :id_producto";

        try (Connection con = sql2o.open()) {
            int affectedRows = con.createQuery(sql)
                    .addParameter("nombre", producto.getNombre())
                    .addParameter("descripcion", producto.getDescripcion())
                    .addParameter("precio", producto.getPrecio())
                    .addParameter("stock", producto.getStock())
                    .addParameter("estado", producto.getEstado())
                    .addParameter("id_categoria", producto.getId_categoria())
                    .addParameter("id_producto", producto.getIdProducto())
                    .executeUpdate()
                    .getResult();

            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProducto(Integer id) {
        String sql = "DELETE FROM producto WHERE id_producto = :id";

        try (Connection con = sql2o.open()) {
            int affectedRows = con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate()
                    .getResult();

            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
