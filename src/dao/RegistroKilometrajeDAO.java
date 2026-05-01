package dao;

import model.RegistroKilometraje;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroKilometrajeDAO {

    public void insertar(RegistroKilometraje registro, Connection conexion) throws SQLException {

        String sql = "INSERT INTO registroKilometraje(idCamion, kilometrajeRecorrido) VALUES (?, ?)";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, registro.getIdCamion());
        ps.setInt(2, registro.getKilometrajeRecorrido());

        ps.executeUpdate();
    }

}
