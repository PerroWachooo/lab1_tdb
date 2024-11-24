package tbd.lab1.models;

public class UsuarioActivo {
    private String usuario;
    private long total_queries;

    // Constructor sin argumentos
    public UsuarioActivo() {
    }
    // Constructor
    public UsuarioActivo(String usuario, long totalQueries) {
        this.usuario = usuario;
        this.total_queries = totalQueries;
    }

    // Getters y Setters
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public long getTotal_queries() {
        return total_queries;
    }

    public void setTotal_queries(long total_queries) {
        this.total_queries = total_queries;
    }
}
