package dao;

import model.InventarioPieza;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InventarioPiezaDAO {

    public int insertar(InventarioPieza p, Connection con) throws SQLException {
        String sql = "INSERT INTO inventarioPiezas(nombre, tipo, cantidad, estado, descripcion) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getTipo());
        ps.setInt(3, p.getCantidad());
        ps.setString(4, p.getEstado());
        ps.setString(5, p.getDescripcion());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public boolean actualizarCantidad(int idPieza, int nuevaCantidad, Connection con) throws SQLException {
        String sql = "UPDATE inventarioPiezas SET cantidad=? WHERE idPieza=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, nuevaCantidad);
        ps.setInt(2, idPieza);

        return ps.executeUpdate() > 0;
    }

    public int obtenerCantidad(int idPieza, Connection con) throws SQLException {
        String sql = "SELECT cantidad FROM inventarioPiezas WHERE idPieza=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idPieza);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("cantidad");
        }

        return 0;
    }

    public List<InventarioPieza> listar(Connection con) throws SQLException {
        List<InventarioPieza> lista = new ArrayList<>();

        String sql = "SELECT idPieza, nombre, tipo, cantidad, estado, descripcion FROM inventarioPiezas";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            InventarioPieza p = new InventarioPieza();
            p.setIdPieza(rs.getInt("idPieza"));
            p.setNombre(rs.getString("nombre"));
            p.setTipo(rs.getString("tipo"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setEstado(rs.getString("estado"));
            p.setDescripcion(rs.getString("descripcion"));

            lista.add(p);
        }

        return lista;
    }

    public boolean eliminar(int idPieza, Connection con) throws SQLException {
        String sql = "DELETE FROM inventarioPiezas WHERE idPieza=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idPieza);

        return ps.executeUpdate() > 0;
    }

    public boolean actualizar(InventarioPieza p, Connection con) throws SQLException {
        String sql = "UPDATE inventarioPiezas SET nombre=?, tipo=?, estado=?, descripcion=? WHERE idPieza=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getNombre());
        ps.setString(2, p.getTipo());
        ps.setString(3, p.getEstado());
        ps.setString(4, p.getDescripcion());
        ps.setInt(5, p.getIdPieza());

        return ps.executeUpdate() > 0;
    }

    public List<InventarioPieza> buscar(String texto, Connection con) throws SQLException {
        List<InventarioPieza> lista = new ArrayList<>();

        String sql = """
        SELECT idPieza, nombre, tipo, cantidad, estado, descripcion
        FROM inventarioPiezas
        WHERE nombre LIKE ? OR tipo LIKE ?
    """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "%" + texto + "%");
        ps.setString(2, "%" + texto + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            InventarioPieza p = new InventarioPieza();
            p.setIdPieza(rs.getInt("idPieza"));
            p.setNombre(rs.getString("nombre"));
            p.setTipo(rs.getString("tipo"));
            p.setCantidad(rs.getInt("cantidad"));
            p.setEstado(rs.getString("estado"));
            p.setDescripcion(rs.getString("descripcion"));

            lista.add(p);
        }

        return lista;
    }

}
