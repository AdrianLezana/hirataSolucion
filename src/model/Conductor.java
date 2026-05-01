package model;

public class Conductor {

    private int idConductor;
    private String nombre;
    private String licencia;
    private String telefono;
    private int idUsuario; //FK

    public Conductor() {
    }

    public Conductor(String nombre, String licencia, String telefono) {
        this.nombre = nombre;
        this.licencia = licencia;
        this.telefono = telefono;
    }

    /*
    public Conductor(int idConductor, String nombre, String licencia, String telefono, int idUsuario) {
        this.idUsuario = idUsuario;
        this.idConductor = idConductor;
        this.nombre = nombre;
        this.licencia = licencia;
        this.telefono = telefono;
    }
    */
    
    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

}
