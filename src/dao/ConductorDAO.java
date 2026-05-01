package dao;

import model.Conductor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConductorDAO {

    public int insertar(Conductor conductor, Connection conexion) throws SQLException {

        String sql = "INSERT INTO conductores(nombre, licencia, telefono, idUsuario) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, conductor.getNombre());
        ps.setString(2, conductor.getLicencia());
        ps.setString(3, conductor.getTelefono());
        ps.setInt(4, conductor.getIdUsuario());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();

        return rs.getInt(1); // idConductor
    }

}
