package model;

public class RegistroKilometraje {

    private int idCamion;
    private int kilometrajeRecorrido;

    public RegistroKilometraje(int idCamion, int kilometrajeRecorrido) {
        this.idCamion = idCamion;
        this.kilometrajeRecorrido = kilometrajeRecorrido;
    }

    public int getIdCamion() {
        return idCamion;
    }

    public void setIdCamion(int idCamion) {
        this.idCamion = idCamion;
    }

    public int getKilometrajeRecorrido() {
        return kilometrajeRecorrido;
    }

    public void setKilometrajeRecorrido(int kilometrajeRecorrido) {
        this.kilometrajeRecorrido = kilometrajeRecorrido;
    }

}
