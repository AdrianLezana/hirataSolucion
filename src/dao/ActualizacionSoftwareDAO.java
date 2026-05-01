package dao;

import model.ActualizacionSoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class ActualizacionSoftwareDAO {

    public boolean insertar(ActualizacionSoftware a, Connection con) throws SQLException {
        String sql = "INSERT INTO actualizacionSoftware(idEquipo, idSoftware, fecha, descripcion) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdEquipo());
        ps.setInt(2, a.getIdSoftware());
        ps.setString(3, a.getFecha());
        ps.setString(4, a.getDescripcion());

        return ps.executeUpdate() > 0;

    }

    public boolean actualizar(ActualizacionSoftware a, Connection con) throws SQLException {
        String sql = "UPDATE actualizacionSoftware SET idEquipo=?, idSoftware=?, fecha=?, descripcion=? WHERE idActualizacion=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, a.getIdEquipo());
        ps.setInt(2, a.getIdSoftware());
        ps.setString(3, a.getFecha());
        ps.setString(4, a.getDescripcion());
        ps.setInt(5, a.getIdActualizacion());

        return ps.executeUpdate() > 0;

    }

    public boolean eliminar(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM actualizacionSoftware WHERE idActualizacion=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;

    }

    public List<Object[]> listar(Connection con) throws SQLException {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT a.idActualizacion,
               e.nombre AS equipo,
               s.nombre AS software,
               s.version,
               a.fecha,
               a.descripcion
        FROM actualizacionSoftware a
        INNER JOIN equipos e ON a.idEquipo = e.idEquipo
        INNER JOIN software s ON a.idSoftware = s.idSoftware
    """;

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Object[]{
                rs.getInt("idActualizacion"),
                rs.getString("equipo"),
                rs.getString("software"),
                rs.getString("version"),
                rs.getString("fecha"),
                rs.getString("descripcion")
            });
        }

        return lista;

    }

}
