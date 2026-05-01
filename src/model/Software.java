package model;

public class Software {

    private int idSoftware;
    private String nombre;
    private String version;

    public Software() {
    }

    public Software(String nombre, String version) {
        this.nombre = nombre;
        this.version = version;
    }

    public int getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

}
