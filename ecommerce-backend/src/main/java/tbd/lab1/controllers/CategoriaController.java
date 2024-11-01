package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.services.CategoriaService;

import java.util.List;

@RestController
<<<<<<< HEAD
@RequestMapping("/categorias")
@CrossOrigin("*")
=======
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*") // Permite acceso desde el frontend en cualquier origen
>>>>>>> Andre
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

<<<<<<< HEAD
    @PostMapping("/")
    public ResponseEntity<CategoriaEntity> saveCategoria(@RequestBody CategoriaEntity categoria) {
        CategoriaEntity NewCategoria = categoriaService.saveCategoria(categoria);
        return ResponseEntity.ok(NewCategoria);
    }

    //todas las categorias
    @GetMapping("/")
    public ResponseEntity<List<CategoriaEntity>> listCategorias() {
        List<CategoriaEntity> categorias = categoriaService.getCategorias();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/id-categoria/{id}")
    public ResponseEntity<CategoriaEntity> getCategoriaById(@PathVariable Long id) {
        CategoriaEntity categoria =categoriaService.getCategoryById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @PutMapping("/")
    public ResponseEntity<CategoriaEntity> updateCategoria(@RequestBody CategoriaEntity categoria) {
        boolean isUpdated = categoriaService.updateCategoria(categoria);
        if (isUpdated) {
            return ResponseEntity.ok(categoria);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-categoria/{id}")
    public ResponseEntity<Boolean> deleteCategoriaById(@PathVariable Long id) throws Exception {
        var isDeleted = categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }



=======
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> getCategoriaById(@PathVariable int id) {
        CategoriaEntity categoria = categoriaService.getCategoriaById(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping("")
    public ResponseEntity<List<CategoriaEntity>> getAllCategorias() {
        return ResponseEntity.ok().body(categoriaService.getAllCategorias());
    }

    @PostMapping("")
    public ResponseEntity<CategoriaEntity> createCategoria(@RequestBody CategoriaEntity categoria) {
        return ResponseEntity.ok().body(categoriaService.createCategoria(categoria));
    }
    
>>>>>>> Andre
}
