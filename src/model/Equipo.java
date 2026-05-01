package model;

public class Equipo {

    private int idEquipos;
    private String nombre;
    private String tipo;
    private String marca;
    private String modelo;
    private String estado;

    public Equipo() {
    }

    public Equipo(String nombre, String tipo, String marca, String modelo, String estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.marca = marca;
        this.modelo = modelo;
        this.estado = estado;
    }

    public int getIdEquipo() {
        return idEquipos;
    }

    public void setIdEquipo(int idEquipos) {
        this.idEquipos = idEquipos;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
