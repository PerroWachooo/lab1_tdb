package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.DetalleOrdenEntity;
import tbd.lab1.entities.ProductoEntity;
import tbd.lab1.services.DetalleOrdenService;

import java.util.List;

@RestController
@RequestMapping("/detalleordenes")
@CrossOrigin("*")
public class DetalleOrdenController {
    @Autowired
    DetalleOrdenService detalleOrdenService;

    @PostMapping("/")
    public ResponseEntity<DetalleOrdenEntity> saveDetalle(@RequestBody DetalleOrdenEntity detalle) {
        DetalleOrdenEntity NewDetalle = detalleOrdenService.saveDetalle(detalle);
        return ResponseEntity.ok(NewDetalle);
    }

    @GetMapping("/id-detalle/{id}")
    public ResponseEntity<DetalleOrdenEntity> getDetalleById(@PathVariable Long id) {
        DetalleOrdenEntity detalle = detalleOrdenService.getDetalleById(id);
        return ResponseEntity.ok(detalle);
    }

    @GetMapping("/")
    public ResponseEntity<List<DetalleOrdenEntity>> listDetalle() {
        List<DetalleOrdenEntity> detalle = detalleOrdenService.getDetalle();
        return ResponseEntity.ok(detalle);
    }

    @PutMapping("/")
    public ResponseEntity<DetalleOrdenEntity> updateDetalle(@RequestBody DetalleOrdenEntity detalle) {
        boolean isUpdated = detalleOrdenService.updateDetalle(detalle);
        if (isUpdated) {
            return ResponseEntity.ok(detalle);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete-detalle/{id}")
    public ResponseEntity<Boolean> deleteDetalleById(@PathVariable Long id) throws Exception {
        var isDeleted = detalleOrdenService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
