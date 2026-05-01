package service;

import java.sql.Connection;
import model.Conexion;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.ResultSet;

public class RegistroKilometrajeService {

    public void registrarKilometraje(int idCamion, int kmRecorrido) {

        Connection conexion = null;

        try {
            Conexion con = new Conexion();
            conexion = con.getConnection();

            conexion.setAutoCommit(false);

            // 1. Insertar registro
            String sqlInsert = "INSERT INTO registroKilometraje(idCamion, kilometrajeRecorrido) VALUES (?, ?)";
            PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);
            psInsert.setInt(1, idCamion);
            psInsert.setInt(2, kmRecorrido);
            psInsert.executeUpdate();

            // 2. Actualizar kilometraje total
            String sqlUpdate = "UPDATE camiones SET kilometrajeTotal = kilometrajeTotal + ? WHERE idCamion = ?";
            PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, kmRecorrido);
            psUpdate.setInt(2, idCamion);
            psUpdate.executeUpdate();

            // Obtener kilometraje actual del camión
            String sqlSelect = "SELECT kilometrajeTotal, kilometrajeUltimoMantenimiento FROM camiones WHERE idCamion = ?";
            PreparedStatement psSelect = conexion.prepareStatement(sqlSelect);
            psSelect.setInt(1, idCamion);

            ResultSet rs = psSelect.executeQuery();

            if (rs.next()) {
                int kmTotal = rs.getInt("kilometrajeTotal");
                int kmUltMant = rs.getInt("kilometrajeUltimoMantenimiento");

                int kmDesdeMant = kmTotal - kmUltMant;

                if (kmDesdeMant >= 5000 && kmDesdeMant - kmRecorrido < 5000) {
                    JOptionPane.showMessageDialog(null,
                            "El camión ha recorrido " + kmDesdeMant + " km desde el último mantenimiento.");
                }
            }

            conexion.commit();

            //JOptionPane.showMessageDialog(null, "Kilometraje registrado correctamente");
        } catch (Exception e) {

            try {
                if (conexion != null) {
                    conexion.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}
