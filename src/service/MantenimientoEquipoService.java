package service;

import model.TecnicoCombo;
import dao.MantenimientoEquipoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.Conexion;
import model.MantenimientoEquipo;

public class MantenimientoEquipoService {

    private MantenimientoEquipoDAO dao = new MantenimientoEquipoDAO();

    //REGISTRAR
    public boolean registrar(MantenimientoEquipo m) {
        try (Connection con = Conexion.getConnection()) {
            return dao.insertar(m, con);
        } catch (SQLException e) {
            System.out.println("Error registrar: " + e.getMessage());
            return false;
        }
    }

    //EDITAR
    public boolean actualizar(MantenimientoEquipo m) {
        try (Connection con = Conexion.getConnection()) {
            return dao.actualizar(m, con);
        } catch (SQLException e) {
            System.out.println("Error actualizar: " + e.getMessage());
            return false;
        }
    }

    //ELIMINAR
    public boolean eliminar(int id) {
        try (Connection con = Conexion.getConnection()) {
            return dao.eliminar(id, con);
        } catch (SQLException e) {
            System.out.println("Error eliminar: " + e.getMessage());
            return false;
        }
    }

    //LISTAR TÉCNICOS (para cmbTecnico)
    public List<TecnicoCombo> obtenerTecnicos() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listarTecnicos(con);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public List<Object[]> listarMantenimientos() {
        try (Connection con = Conexion.getConnection()) {
            return dao.listarMantenimientos(con);
        } catch (SQLException e) {
            System.out.println("Error listar mantenimientos: " + e.getMessage());
            return null;
        }
    }

}
