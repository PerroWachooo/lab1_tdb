package tbd.lab1.entities;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleOrdenEntity {

    private Long id_detalle;

    private Long id_orden;

    private Long id_producto;

    private Integer cantidad;

    private BigDecimal precio_unitario;
}
