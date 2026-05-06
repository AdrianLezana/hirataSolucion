package dao;

import model.TecnicoCombo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MantenimientoEquipo;

public class MantenimientoEquipoDAO {

    public boolean insertar(MantenimientoEquipo m, Connection con) throws SQLException {
        String sql = "INSERT INTO mantenimientoEquipos(idEquipo, idTecnico, tipoMantenimiento, descripcion, observaciones, fecha) VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, m.getIdEquipo());
        ps.setInt(2, m.getIdTecnico());
        ps.setString(3, m.getTipoMantenimiento());
        ps.setString(4, m.getDescripcion());
        ps.setString(5, m.getObservaciones());
        ps.setString(6, m.getFecha());

        return ps.executeUpdate() > 0;
    }

    public boolean actualizar(MantenimientoEquipo m, Connection con) throws SQLException {
        String sql = "UPDATE mantenimientoEquipos SET idEquipo=?, idTecnico=?, tipoMantenimiento=?, descripcion=?, observaciones=?, fecha=? WHERE idMantenimiento=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, m.getIdEquipo());
        ps.setInt(2, m.getIdTecnico());
        ps.setString(3, m.getTipoMantenimiento());
        ps.setString(4, m.getDescripcion());
        ps.setString(5, m.getObservaciones());
        ps.setString(6, m.getFecha());
        ps.setInt(7, m.getIdMantenimiento());

        return ps.executeUpdate() > 0;
    }

    public boolean eliminar(int id, Connection con) throws SQLException {
        String sql = "DELETE FROM mantenimientoEquipos WHERE idMantenimiento=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);

        return ps.executeUpdate() > 0;
    }

    public List<TecnicoCombo> listarTecnicos(Connection con) throws SQLException {
        List<TecnicoCombo> lista = new ArrayList<>();

        String sql = "SELECT idTecnico, nombreReal FROM tecnicos";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new TecnicoCombo(
                    rs.getInt("idTecnico"),
                    rs.getString("nombreReal")
            ));
        }

        return lista;
    }

    public List<Object[]> listarMantenimientos(Connection con) throws SQLException {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT 
                    m.idMantenimiento,
                    m.idEquipo,
                    CONCAT(e.nombre, ' - ', e.marca, ' ', e.modelo) AS equipo,
                    t.nombreReal,
                    m.tipoMantenimiento,
                    m.descripcion,
                    m.observaciones,
                    m.fecha
                FROM mantenimientoEquipos m
                INNER JOIN tecnicos t ON m.idTecnico = t.idTecnico
                INNER JOIN equipos e ON m.idEquipo = e.idEquipo
    """;

        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Object[]{
                rs.getInt("idMantenimiento"),
                rs.getInt("idEquipo"),
                rs.getString("equipo"),
                rs.getString("nombreReal"),
                rs.getString("tipoMantenimiento"),
                rs.getString("descripcion"),
                rs.getString("observaciones"),
                rs.getString("fecha")
            });
        }

        return lista;
    }

    public List<Object[]> buscarMantenimientos(String texto, Connection con) throws SQLException {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
        SELECT 
            m.idMantenimiento,
            m.idEquipo,
            CONCAT(e.nombre, ' - ', e.marca, ' ', e.modelo) AS equipo,
            t.nombreReal,
            m.tipoMantenimiento,
            m.descripcion,
            m.observaciones,
            m.fecha
        FROM mantenimientoEquipos m
        INNER JOIN tecnicos t ON m.idTecnico = t.idTecnico
        INNER JOIN equipos e ON m.idEquipo = e.idEquipo
        WHERE 
            e.nombre LIKE ? OR
            e.marca LIKE ? OR
            e.modelo LIKE ? OR
            t.nombreReal LIKE ? OR
            m.tipoMantenimiento LIKE ?
    """;

        PreparedStatement ps = con.prepareStatement(sql);

        String filtro = "%" + texto + "%";

        ps.setString(1, filtro);
        ps.setString(2, filtro);
        ps.setString(3, filtro);
        ps.setString(4, filtro);
        ps.setString(5, filtro);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Object[]{
                rs.getInt("idMantenimiento"),
                rs.getInt("idEquipo"),
                rs.getString("equipo"),
                rs.getString("nombreReal"),
                rs.getString("tipoMantenimiento"),
                rs.getString("descripcion"),
                rs.getString("observaciones"),
                rs.getString("fecha")
            });
        }

        return lista;
    }

}
