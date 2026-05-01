package service;

import dao.SoftwareDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Conexion;
import model.Software;
import model.SoftwareCombo;

public class SoftwareService {

    private SoftwareDAO dao = new SoftwareDAO();

    public boolean registrar(Software s) {
        try (Connection con = Conexion.getConnection()) {
            return dao.insertar(s, con);
        } catch (SQLException e) {
            System.out.println("Error registrar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Software s) {
        try (Connection con = Conexion.getConnection()) {
            return dao.actualizar(s, con);
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

    public List<Software> listar() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listar(con);
        } catch (SQLException e) {
            System.out.println("Error listar: " + e.getMessage());
            return null;
        }
    }

    public List<SoftwareCombo> obtenerSoftwareCombo() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listarParaCombo(con);
        } catch (SQLException e) {
            System.out.println("Error listar software: " + e.getMessage());
            return null;
        }
    }

}
