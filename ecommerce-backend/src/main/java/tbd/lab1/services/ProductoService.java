package tbd.lab1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tbd.lab1.entities.ProductoEntity;
import tbd.lab1.repositories.ProductoRepository;

import java.util.ArrayList;

@Service
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;
/*
    public ProductoEntity saveProducto(ProductoEntity producto){
        return productoRepository.saveProducto(producto);
    }

    //obtiene producto segun su id
    public ProductoEntity getProductoById(Long id){
        return productoRepository.getProductoById(id);
    }

    public ArrayList<ProductoEntity> getProductos(){
        return (ArrayList<ProductoEntity>) productoRepository.getProductos();
    }

    public boolean updateProducto(ProductoEntity producto) {
        // vemos si el producto existe en la base de datos
        if (productoRepository.getProductoById(producto.getIdProducto()) != null) {
            // actualizamos el product usando el método del repositorio
            return productoRepository.updateProducto(producto);
        }
        return false;
    }

    public boolean deleteProducto(Long id) throws Exception {
        try{
            productoRepository.deleteProducto(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

 */
}
