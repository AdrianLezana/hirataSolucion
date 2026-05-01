package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Conexion;

public class Login {

    public boolean validarUsuario(String usuario, String clave) {
        String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // true si existe

        } catch (SQLException e) {
            System.out.println("Error en login usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean validarTecnico(String usuario, String clave) {
        String sql = "SELECT * FROM tecnicos WHERE usuario = ? AND clave = ?";

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ps.setString(2, clave);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error en login técnico: " + e.getMessage());
            return false;
        }
    }
}
