
package model;


public class TecnicoCombo {
    
    private int id;
    private String nombre;

    public TecnicoCombo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return nombre; // Esto es lo que se muestra en el JComboBox
    }   
    
}
