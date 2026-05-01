package model;

public class ActualizacionSoftware {

    private int idActualizacion;
    private int idEquipo;
    private int idSoftware;
    private String fecha;
    private String descripcion;

    public ActualizacionSoftware() {
    }

    public ActualizacionSoftware(int idEquipo, int idSoftware, String fecha, String descripcion) {
        this.idEquipo = idEquipo;
        this.idSoftware = idSoftware;
        this.fecha = fecha;
        this.descripcion = descripcion;
    }

    public int getIdActualizacion() {
        return idActualizacion;
    }

    public void setIdActualizacion(int idActualizacion) {
        this.idActualizacion = idActualizacion;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdSoftware() {
        return idSoftware;
    }

    public void setIdSoftware(int idSoftware) {
        this.idSoftware = idSoftware;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
