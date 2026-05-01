package service;

import dao.ActualizacionSoftwareDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.ActualizacionSoftware;
import model.Conexion;

public class ActualizacionSoftwareService {

    private ActualizacionSoftwareDAO dao = new ActualizacionSoftwareDAO();

    public boolean registrar(ActualizacionSoftware a) {
        try (Connection con = Conexion.getConnection()) {
            return dao.insertar(a, con);
        } catch (SQLException e) {
            System.out.println("Error registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(ActualizacionSoftware a) {
        try (Connection con = Conexion.getConnection()) {
            return dao.actualizar(a, con);
        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        try (Connection con = Conexion.getConnection()) {
            return dao.eliminar(id, con);
        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    public List<Object[]> listar() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listar(con);
        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
            return null;
        }
    }
}
