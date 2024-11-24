package tbd.lab1.models;

public class UsuarioActivo {
    private String usuario;
    private long totalQueries;

    // Constructor
    public UsuarioActivo(String usuario, long totalQueries) {
        this.usuario = usuario;
        this.totalQueries = totalQueries;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long getTotalQueries() {
        return totalQueries;
    }

    public void setTotalQueries(long totalQueries) {
        this.totalQueries = totalQueries;
    }
}
