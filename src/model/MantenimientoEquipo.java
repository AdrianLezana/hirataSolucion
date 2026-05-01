
package model;


public class MantenimientoEquipo {
    
    private int idMantenimiento;
    private int idEquipo;
    private int idTecnico;
    private String tipoMantenimiento;
    private String descripcion;
    private String observaciones;
    private String fecha;

    public MantenimientoEquipo() {
    }

    public MantenimientoEquipo(String tipoMantenimiento, String descripcion, String observaciones, String fecha) {
        this.tipoMantenimiento = tipoMantenimiento;
        this.descripcion = descripcion;
        this.observaciones = observaciones;
        this.fecha = fecha;
    }

    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
}
