package service;

import java.sql.Connection;
import model.Conexion;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;

public class RegistroMantenimientoService {

    public void registrarMantenimiento(int idCamion, String tipo, String descripcion) {

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            // Insertar mantenimiento
            String sqlInsert = "INSERT INTO mantenimiento(idCamion, tipoMantenimiento, descripcion) VALUES (?, ?, ?)";
            PreparedStatement psInsert = conexion.prepareStatement(sqlInsert);
            psInsert.setInt(1, idCamion);
            psInsert.setString(2, tipo);
            psInsert.setString(3, descripcion);
            psInsert.executeUpdate();

            // Reiniciar contador
            String sqlUpdate = "UPDATE camiones SET kilometrajeUltimoMantenimiento = kilometrajeTotal WHERE idCamion = ?";
            PreparedStatement psUpdate = conexion.prepareStatement(sqlUpdate);
            psUpdate.setInt(1, idCamion);
            psUpdate.executeUpdate();

            JOptionPane.showMessageDialog(null, "Mantenimiento registrado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void actualizarMantenimiento(int idMantenimiento, String tipo, String descripcion) {

        String sql = "UPDATE mantenimiento SET tipoMantenimiento = ?, descripcion = ? WHERE idMantenimiento = ?";

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, tipo);
            ps.setString(2, descripcion);
            ps.setInt(3, idMantenimiento);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarMantenimiento(int idMantenimiento) {

        String sql = "DELETE FROM mantenimiento WHERE idMantenimiento = ?";

        try {
            Conexion con = new Conexion();
            Connection conexion = con.getConnection();

            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMantenimiento);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
