package tbd.lab1.entities;

import lombok.Data;
import java.math.BigDecimal;

@Data

public class DetalleOrdenEntity {

    private Integer id_detalle;

    //este era la entidad orden, se cambio por integer
    private Integer id_orden;

    //este era la entidad producto, se cambio por integer
    private Integer id_producto;

    private Integer cantidad;

    private BigDecimal precio_unitario;
}
