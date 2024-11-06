package tbd.lab1.entities;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Data

public class OrdenEntity {

    private Long idOrden;

    private LocalDateTime fechaOrden;

    private String estado;

    private ClienteEntity cliente;

    private BigDecimal total;
}
