package tbd.lab1.entities;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data

public class OrdenEntity {

    private Integer id_orden;

    private LocalDateTime fecha_orden;

    private String estado;

    private ClienteEntity cliente;

    private BigDecimal total;
}
