package tbd.lab1.entities;

import lombok.Data;


@Data

public class ClienteEntity {

    private Integer id_cliente;

    private String nombre;

    private String direccion;

    private String email;

    private String telefono;
}
