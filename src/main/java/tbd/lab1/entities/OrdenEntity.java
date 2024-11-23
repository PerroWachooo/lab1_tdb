package tbd.lab1.entities;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data
public class OrdenEntity {

    private Long id_orden;

    private LocalDateTime fecha_orden;

    private String estado;

    private Long id_cliente;

    private BigDecimal total;
}
