package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.entities.ClienteEntity;
import tbd.lab1.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin("*")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

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



}
