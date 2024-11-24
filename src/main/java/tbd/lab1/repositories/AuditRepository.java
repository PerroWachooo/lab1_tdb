package tbd.lab1.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import tbd.lab1.models.ClienteMultiplesCompras;
import tbd.lab1.models.UsuarioActivo;

import java.util.List;

@Repository
public class AuditRepository implements AuditRepositoryInt {

    @Autowired
    private Sql2o sql2o;

    public List<UsuarioActivo> reporteUsuariosMasActivos() {
        String sql = "SELECT * FROM reporte_usuarios_mas_activos()";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .executeAndFetch(UsuarioActivo.class);
        }
    }

    public List<ClienteMultiplesCompras> clientesMultiplesCompras(String fechaInicio, String fechaFin) {
        String sql = "CALL clientes_multiples_compras_y_productos(:fechaInicio, :fechaFin)";

        try (Connection connection = sql2o.open()) {
            return connection.createQuery(sql)
                    .addParameter("fechaInicio", fechaInicio)
                    .addParameter("fechaFin", fechaFin)
                    .executeAndFetch(ClienteMultiplesCompras.class);
        }
    }

}

