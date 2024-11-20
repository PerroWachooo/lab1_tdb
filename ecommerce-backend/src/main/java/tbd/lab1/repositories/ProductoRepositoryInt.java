package tbd.lab1.repositories;
import java.util.List;

import tbd.lab1.entities.ProductoEntity;

public interface ProductoRepositoryInt {

    ProductoEntity saveProducto(ProductoEntity producto);

    List<ProductoEntity> getProductos();

    ProductoEntity getProductoById(Integer id);

    boolean updateProducto(ProductoEntity producto);

    boolean deleteProducto(Integer id);

}
