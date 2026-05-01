package dao;

import model.Camion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CamionDAO {

    public int insertar(Camion camion, Connection conexion) throws SQLException {

        String sql = "INSERT INTO camiones(marca, modelo, anio, kilometrajeTotal, idConductor) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, camion.getMarca());
        ps.setString(2, camion.getModelo());
        ps.setInt(3, camion.getAnio());
        ps.setInt(4, camion.getKilometrajeTotal()); // 0 por defecto
        ps.setInt(5, camion.getIdConductor());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1); // idCamion
    }

}
