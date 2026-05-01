package clases;

import java.sql.Connection;
import java.sql.SQLException;
import model.Conexion;
import view.frmLogin;

public class Main {

    public static void main(String[] args) {

        // ? Test de conexión (opcional)
        try (Connection con = Conexion.getConnection()) {
            System.out.println("Conexión exitosa.");
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }

        // ? Look and Feel
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ? Iniciar Login
        java.awt.EventQueue.invokeLater(() -> {
            new frmLogin().setVisible(true);
        });
    }
}