package tbd.lab1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ClienteEntity {
    private Long idCliente;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;
}
