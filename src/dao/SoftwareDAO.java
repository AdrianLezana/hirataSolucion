package dao;

import model.Software;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import model.SoftwareCombo;

public class SoftwareDAO {

    public boolean insertar(Software s, Connection con) throws SQLException {
        String sql = "INSERT INTO software(nombre, version) VALUES (?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getNombre());
        ps.setString(2, s.getVersion());

        return ps.executeUpdate() > 0;
    }

    public boolean actualizar(Software s, Connection con) throws SQLException {
        String sql = "UPDATE software SET nombre=?, version=? WHERE idSoftware=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, s.getNombre());
        ps.setString(2, s.getVersion());
        ps.setInt(3, s.getIdSoftware());

        return ps.executeUpdate() > 0;
    }

    public boolean eliminar(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM software WHERE idSoftware=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    }

    public List<Software> listar(Connection con) throws SQLException {
        List<Software> lista = new ArrayList<>();

        String sql = "SELECT * FROM software";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Software s = new Software();
            s.setIdSoftware(rs.getInt("idSoftware"));
            s.setNombre(rs.getString("nombre"));
            s.setVersion(rs.getString("version"));

            lista.add(s);
        }

        return lista;
    }

    public List<SoftwareCombo> listarParaCombo(Connection con) throws SQLException {
        List<SoftwareCombo> lista = new ArrayList<>();

        String sql = "SELECT idSoftware, nombre, version FROM software";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new SoftwareCombo(
                    rs.getInt("idSoftware"),
                    rs.getString("nombre"),
                    rs.getString("version")
            ));
        }

        return lista;
    }

}
