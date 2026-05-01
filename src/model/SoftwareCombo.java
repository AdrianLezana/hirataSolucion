package model;

public class SoftwareCombo {

    private int id;
    private String nombre;
    private String version;

    public SoftwareCombo(int id, String nombre, String version) {
        this.id = id;
        this.nombre = nombre;
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return nombre; // lo que se ve en el combo
    }
}
