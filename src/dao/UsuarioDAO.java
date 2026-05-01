package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Usuario;

public class UsuarioDAO {
    
    public int insertar(Usuario usuario, Connection conexion) throws SQLException {
        String sql = "INSERT INTO usuarios(username, password, rol) VALUES (?, ?, ?)";

        PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, usuario.getUsername());
        ps.setString(2, usuario.getPassword());
        ps.setString(3, usuario.getRol());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        rs.next();
        return rs.getInt(1); // Devuelve el ID
    }
    
}
