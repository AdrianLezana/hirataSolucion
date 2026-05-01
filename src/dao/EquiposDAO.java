package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Equipo;
import model.EquipoCombo;

public class EquiposDAO {

    public int insertar(Equipo equipo, Connection conexion) throws SQLException {
        String sql = "INSERT INTO equipos(nombre, tipo, marca, modelo, estado) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, equipo.getNombre());
        ps.setString(2, equipo.getTipo());
        ps.setString(3, equipo.getMarca());
        ps.setString(4, equipo.getModelo());
        ps.setString(5, equipo.getEstado());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1); // Devuelve el ID

    }

    public boolean actualizar(Equipo equipo, Connection conexion) throws SQLException {
        String sql = "UPDATE equipos SET nombre=?, tipo=?, marca=?, modelo=?, estado=? WHERE idEquipo=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, equipo.getNombre());
        ps.setString(2, equipo.getTipo());
        ps.setString(3, equipo.getMarca());
        ps.setString(4, equipo.getModelo());
        ps.setString(5, equipo.getEstado());
        ps.setInt(6, equipo.getIdEquipo());

        int filas = ps.executeUpdate();
        return filas > 0;
    }

    public boolean eliminar(int idEquipo, Connection conexion) throws SQLException {
        String sql = "DELETE FROM equipos WHERE idEquipo=?";

        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setInt(1, idEquipo);

        int filas = ps.executeUpdate();
        return filas > 0;
    }

    public List<Equipo> listar(Connection conexion) throws SQLException {
        List<Equipo> lista = new ArrayList<>();

        String sql = "SELECT * FROM equipos";
        PreparedStatement ps = conexion.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Equipo eq = new Equipo();
            eq.setIdEquipo(rs.getInt("idEquipo"));
            eq.setNombre(rs.getString("nombre"));
            eq.setTipo(rs.getString("tipo"));
            eq.setMarca(rs.getString("marca"));
            eq.setModelo(rs.getString("modelo"));
            eq.setEstado(rs.getString("estado"));

            lista.add(eq);
        }

        return lista;
    }

    public List<EquipoCombo> listarParaCombo(Connection con) throws SQLException {
        List<EquipoCombo> lista = new ArrayList<>();

        String sql = "SELECT idEquipo, nombre FROM equipos";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new EquipoCombo(
                    rs.getInt("idEquipo"),
                    rs.getString("nombre")
            ));
        }

        return lista;
    }
}
