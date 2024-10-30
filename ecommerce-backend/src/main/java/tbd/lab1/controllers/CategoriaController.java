package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*") // Permite acceso desde el frontend en cualquier origen
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

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
    
}
