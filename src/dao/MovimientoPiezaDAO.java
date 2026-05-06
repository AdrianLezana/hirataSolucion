package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.MovimientoPieza;
import java.sql.ResultSet;

public class MovimientoPiezaDAO {

    // ? INSERTAR MOVIMIENTO (ENTRADA / SALIDA)
    public boolean insertar(MovimientoPieza m, Connection con) throws SQLException {
        String sql = "INSERT INTO movimientoPiezas(idPieza, tipoMovimiento, cantidad, descripcion) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, m.getIdPieza());
        ps.setString(2, m.getTipoMovimiento());
        ps.setInt(3, m.getCantidad());
        ps.setString(4, m.getDescripcion());

        return ps.executeUpdate() > 0;
    }

    // ? LISTAR MOVIMIENTOS POR PIEZA (para JTable historial)
    public List<Object[]> listarPorPieza(int idPieza, Connection con) throws SQLException {
        List<Object[]> lista = new ArrayList<>();

        String sql = """
            SELECT idMovimiento, tipoMovimiento, cantidad, fecha, descripcion
            FROM movimientoPiezas
            WHERE idPieza = ?
            ORDER BY fecha DESC
        """;

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, idPieza);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            lista.add(new Object[]{
                rs.getInt("idMovimiento"),
                rs.getString("tipoMovimiento"),
                rs.getInt("cantidad"),
                rs.getString("fecha"),
                rs.getString("descripcion")
            });
        }

        return lista;
    }

}
