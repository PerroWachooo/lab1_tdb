package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.CategoriaEntity;
import tbd.lab1.services.CategoriaService;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin("*")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaEntity> getCategoriaById(@PathVariable int id) {
        CategoriaEntity categoria =categoriaService.getCategoryById(id);
        return ResponseEntity.ok().body(categoria);
    }
}
