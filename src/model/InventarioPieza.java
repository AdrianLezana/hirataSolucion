package model;

public class InventarioPieza {

    private int idPieza;
    private String nombre;
    private String tipo;
    private int cantidad;
    private String estado;
    private String descripcion;

    public InventarioPieza() {
    }

    public InventarioPieza(String nombre, String tipo, int cantidad, String estado, String descripcion) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
