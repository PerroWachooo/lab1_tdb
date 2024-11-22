package tbd.lab1.entities;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DetalleOrdenEntity {

    private Long id_detalle;

    private OrdenEntity orden;

    private ProductoEntity producto;

    private Integer cantidad;

    private BigDecimal precio_unitario;
}
