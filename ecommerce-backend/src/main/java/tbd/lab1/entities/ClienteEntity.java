package tbd.lab1.entities;

import lombok.Data;


@Data

public class ClienteEntity {

    private Integer idCliente;

    private String nombre;

    private String direccion;

    private String email;

    private String telefono;
}
