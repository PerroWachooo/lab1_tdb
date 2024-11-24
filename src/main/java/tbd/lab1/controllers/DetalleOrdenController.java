package tbd.lab1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tbd.lab1.entities.DetalleOrdenEntity;
import tbd.lab1.services.DetalleOrdenService;

import java.util.List;

@RestController
@RequestMapping("/api/detalleordenes")
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
    public ResponseEntity<DetalleOrdenEntity> getDetalleById(@PathVariable Integer id) {
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
    public ResponseEntity<Boolean> deleteDetalleById(@PathVariable Integer id) throws Exception {
        detalleOrdenService.deleteDetalle(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/gestionarDevolucion")
    public ResponseEntity<String> gestionarDevolucion(
            @RequestParam int idOrden,
            @RequestParam int idProducto,
            @RequestParam int cantidad) {

        detalleOrdenService.gestionarDevolucion(idOrden, idProducto, cantidad);
        return ResponseEntity.ok("Devolución gestionada correctamente.");
    }


}
