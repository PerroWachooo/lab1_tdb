package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.ProductoEntity;
import tbd.lab1.services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin("*")
public class ProductoController {
    @Autowired
    ProductoService productoService;

    @PostMapping("/")
    public ResponseEntity<ProductoEntity> saveProducto(@RequestBody ProductoEntity producto) {
        ProductoEntity NewProducto = productoService.saveProducto(producto);
        System.out.println("Producto recibido: " + producto);

        return ResponseEntity.ok(NewProducto);
    }

    /*
    @GetMapping("/id-producto/{id}")
    public ResponseEntity<ProductoEntity> getProductoById(@PathVariable Long id) {
        ProductoEntity producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }
    */

    @GetMapping("/")
    public ResponseEntity<List<ProductoEntity>> listProducto() {
        List<ProductoEntity> productos = productoService.getProductos();
        return ResponseEntity.ok(productos);
    }

    /*
    // actualiza producto
    @PutMapping("/")
    public ResponseEntity<ProductoEntity> updateProducto(@RequestBody ProductoEntity producto) {
        boolean isUpdated = productoService.updateProducto(producto);
        if (isUpdated) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // borra un solo producto
    @DeleteMapping("/delete-producto/{id}")
    public ResponseEntity<Boolean> deleteProductoById(@PathVariable Long id) throws Exception {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

 */
}
