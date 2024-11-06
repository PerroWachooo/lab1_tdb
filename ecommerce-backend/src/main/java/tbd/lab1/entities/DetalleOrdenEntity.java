package tbd.lab1.entities;

import lombok.Data;
import java.math.BigDecimal;

@Data

public class DetalleOrdenEntity {

    private Long idDetalle;

    private OrdenEntity orden;

    private ProductoEntity producto;

    private Integer cantidad;

    private BigDecimal precioUnitario;
}
