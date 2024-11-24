package tbd.lab1.models;

public class ClienteMultiplesCompras {
    private String cliente;        // Nombre del cliente
    private String fechaOrden;     // Fecha de la orden
    private String producto;       // Nombre del producto
    private long numCompras;       // Número de compras

    // Constructor
    public ClienteMultiplesCompras(String cliente, String fechaOrden, String producto, long numCompras) {
        this.cliente = cliente;
        this.fechaOrden = fechaOrden;
        this.producto = producto;
        this.numCompras = numCompras;
    }

    // Getters y Setters
    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(String fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public long getNumCompras() {
        return numCompras;
    }

    public void setNumCompras(long numCompras) {
        this.numCompras = numCompras;
    }
}
