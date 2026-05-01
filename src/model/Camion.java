package model;

public class Camion {

    private int idCamion;
    private String marca;
    private String modelo;
    private int anio;
    private int kilometrajeTotal;
    private int idConductor; //FK

    public Camion() {
    }

    public Camion(String marca, String modelo, int anio, int kilometrajeTotal) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometrajeTotal = 0;
    }

    /*
    public Camion(int idCamion, String marca, String modelo, int anio, int kilometrajeTotal) {
        this.idCamion = idCamion;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometrajeTotal = kilometrajeTotal;
    }
    */

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
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

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getKilometrajeTotal() {
        return kilometrajeTotal;
    }

    public void setKilometrajeTotal(int kilometrajeTotal) {
        this.kilometrajeTotal = kilometrajeTotal;
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

}
